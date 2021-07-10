package com.sap.sct.idtmbe.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectMapper implements RowMapper<Object> {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> row = new HashMap<>();
        try {
            final int columnCount = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= columnCount; i++) {
                row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
            }
        } catch (Exception e) {

        }

        return row;
    }
}