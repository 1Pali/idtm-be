package com.sap.sct.idtmbe.controller;

import com.sap.sct.idtmbe.api.enumeration.DeletionStatus;
import com.sap.sct.idtmbe.api.service.EntityContentService;
import com.sap.sct.idtmbe.exception.ElementDoesNotExistException;
import com.sap.sct.idtmbe.model.entity.EntityContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/entitycontent")
public class EntityContentController {
    private final EntityContentService entityContentService;

    public EntityContentController(final EntityContentService entityContentService) {
        this.entityContentService = entityContentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityContent>> getListOfEntityContents() {
        return ResponseEntity.ok(entityContentService.getList());
    }

    @GetMapping(value = "/{entitytablename}/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> getEntityContentData(@PathVariable("entitytablename") @NotNull String entityTableName) {
        return ResponseEntity.ok(entityContentService.getEntityContentData(entityTableName));
    }

    @GetMapping(value = "/{entitycontentid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityContent> getEntityContentById(@PathVariable("entitycontentid") @NotNull Long entityContentId) {
        return ResponseEntity.ok(entityContentService.getById(entityContentId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityContent> createEntityContent(@Valid @RequestBody EntityContent dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entityContentService.create(dto));
    }

//    @PutMapping(value = "/{entitycontentid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EntityContent> updateEntityContent(@PathVariable("ingredientid") @NotNull Long entityContentId,
//                                                @RequestBody EntityContent dto) {
//
//        return ResponseEntity.accepted().body(entityContentService.update(entityContentId, dto));
//    }

    @DeleteMapping(value = "/{entitycontentid}")
    public ResponseEntity<Void> deleteEntityContent(@PathVariable("entitycontentid") @NotNull Long entityContentId) {
        entityContentService.delete(entityContentId);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping(value = "/deletelist", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> deleteIngredientList(@RequestBody List<Long> ingredientIdList) {
//        if (ingredientIdList.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty list of Ingredient Ids received");
//        }
//
//        List<EntityContent> deletedIngredientList = new ArrayList<>();
//
//        for (Long ingredientId : ingredientIdList) {
//            try {
//                entityContentService.delete(ingredientId);
//                deletedIngredientList.add(new EntityContent(ingredientId, DeletionStatus.SUCCESS, null));
//            } catch (ElementDoesNotExistException e) {
////                log.error(e.getMessage(), e);
//                deletedIngredientList.add(new RemovedItemDto(ingredientId, DeletionStatus.ERROR, e.getMessage()));
//            }
//        }
//        return ResponseEntity.ok(new Gson().toJson(deletedIngredientList));
//    }

}
