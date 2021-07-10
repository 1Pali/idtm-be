package com.sap.sct.idtmbe.api.enumeration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.sct.idtmbe.exception.CatMappingException;
import com.sap.sct.idtmbe.model.catalog.CatAssocitation;

import java.util.HashMap;
import java.util.Map;

public enum Association {
    IS_PRIMARY("PRIMARY KEY"),
    CONSTRAINT("CONSTRAINT");

    @JsonIgnore
    public final String label;

    private static final Map<String, Association> idMap = new HashMap<>();
    private static final Map<String, Association> nameMap = new HashMap<>();

    static {
        for (Association type : Association.values()) {
            idMap.put(type.getLabel(), type);
            nameMap.put(type.name().toUpperCase(), type);
        }
    }


    Association(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Association fromLabel(String label) {
        final Association associationType = idMap.getOrDefault(label, null);
        if (associationType == null) {
            throw new CatMappingException(String.format("Missing Enum(%s) for label(%s)", Association.class.getName(), label));
        }
        return associationType;
    }

    public static Association fromName(String name) {
        final Association associationType = nameMap.getOrDefault(name, null);
        if (associationType == null) {
            throw new CatMappingException(
                    String.format("Missing Enum(%s) for name(%s)", Association.class.getName(), name));
        }
        return associationType;
    }

    public static Association fromCatIngredientType(CatAssocitation catAssocitation) {
        return fromLabel(catAssocitation.getLabel());
    }

    public CatAssocitation toCatAssociation() {
        return new CatAssocitation(getLabel(), name());
    }
}
