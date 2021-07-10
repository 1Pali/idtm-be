package com.sap.sct.idtmbe.api.enumeration;

public enum DataType {
    INTEGER("integer"),
    DATE("date"),
    VARCHAR("varchar");
    public final String label;

    private DataType(String label) {
        this.label = label;
    }
}
