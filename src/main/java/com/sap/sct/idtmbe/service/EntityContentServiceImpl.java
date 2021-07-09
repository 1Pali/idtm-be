package com.sap.sct.idtmbe.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sap.sct.idtmbe.api.service.EntityContentService;
import com.sap.sct.idtmbe.exception.ElementDoesNotExistException;
import com.sap.sct.idtmbe.model.entity.EntityContent;
import com.sap.sct.idtmbe.repository.EntityContentRepository;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EntityContentServiceImpl implements EntityContentService {
    private static final String ENTITY_TABLE_DOES_NOT_EXISTS_EXCEPTION_MESSAGE = "Entity Table with id: %s does not exist.";
    private static final String ENTITY_TABLE_EXISTS_EXCEPTION_MESSAGE = "Entity Table with name: %s already exist.";
    private final EntityContentRepository entityContentRepository;
    private final JdbcTemplate jdbcTemplate;

    public EntityContentServiceImpl(final EntityContentRepository entityContentRepository, final JdbcTemplate jdbcTemplate) {

        this.entityContentRepository = entityContentRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public EntityContent getById(Long id) {
        return entityContentRepository.findById(id).orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(ENTITY_TABLE_DOES_NOT_EXISTS_EXCEPTION_MESSAGE, id)
                ));
    }

    @Override
    @Transactional
    public List<EntityContent> getList() {
        return entityContentRepository.findAll()
                .stream().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        EntityContent existingTable = entityContentRepository.findById(id)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(ENTITY_TABLE_DOES_NOT_EXISTS_EXCEPTION_MESSAGE, id)));

        final String query = "drop table " + existingTable.getTableName();
        jdbcTemplate.execute(query);
        entityContentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public EntityContent update(Long tableId, EntityContent dto) {
        EntityContent existingEntityContent = entityContentRepository.findById(tableId)
                .orElseThrow(() -> new ElementDoesNotExistException(
                        String.format(ENTITY_TABLE_DOES_NOT_EXISTS_EXCEPTION_MESSAGE, tableId)));

        existingEntityContent.setTableName(dto.getTableName());

        return entityContentRepository.save(existingEntityContent);
    }

    @Override
    @Transactional
    public EntityContent create(EntityContent dto) {
        Optional<EntityContent> existingTable = entityContentRepository.findByName(dto.getTableName());
        if(existingTable.isPresent()){
            throw new ElementDoesNotExistException(
                    String.format(ENTITY_TABLE_EXISTS_EXCEPTION_MESSAGE, dto.getTableName()));
        }
        try {
            final Map<String, Object> content = new JSONParser(dto.getTableContent()).object();
            final List<JsonObject> tableColumns = (ArrayList<JsonObject>)content.get("columns");
            StringBuilder query = new StringBuilder();
            query.append("CREATE TABLE " + dto.getTableName() + "(");
            for(int i = 0; i < tableColumns.size(); i++ ) {
                String name = ((Map<String, Object>)((List) tableColumns).get(i)).get("name").toString();
                String type = ((Map<String, Object>)((List) tableColumns).get(i)).get("type").toString();
                query.append(name + " " + type + ", ");
            }
            final String finalQuery = query.toString().substring(0, query.toString().length() - 2) + ")";
            jdbcTemplate.execute(finalQuery);
        } catch (Exception e) {

        }

        return entityContentRepository.save(dto);
    }
}
