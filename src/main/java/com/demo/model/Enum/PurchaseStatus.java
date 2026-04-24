package com.demo.model.Enum;

public enum PurchaseStatus {
    PENDING("Pendiente"),
    IN_PROGRESS("En proceso"),
    FINISHED("Finalizada"),
    CANCELADO("Cancelado");

    private final String label;

    PurchaseStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
