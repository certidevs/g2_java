package com.demo.model.enums;

public enum PurchaseStatus {
    PENDING("Pendiente"),
    IN_PROGRESS("En proceso"),
    FINISHED("Finalizada"),
    CANCEL("Cancelado");

    private final String label;

    PurchaseStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
