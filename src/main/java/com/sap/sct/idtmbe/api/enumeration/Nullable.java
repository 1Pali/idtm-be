package com.sap.sct.idtmbe.api.enumeration;

public enum Nullable {
    NULL("NULL"), NOT_NULL("NOT NULL");
    public final String label;

    private Nullable(String label) {
        this.label = label;
    }
}
