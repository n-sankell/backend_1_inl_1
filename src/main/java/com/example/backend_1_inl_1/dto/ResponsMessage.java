package com.example.backend_1_inl_1.dto;

public enum ResponsMessage {

    NOT_A_NUMBER("Please provide a valid number."),

    PRODUCT_ADDED(" was added to the database."),
    ORDER_COMPLETE("Purchase complete."),
    PRODUCT_NOT_FOUND("Product was not found."),
    CUSTOMER_NOT_FOUND("Customer was not found."),
    NOTHING_FOUND("Product and customer was not found."),

    EMAIL_IN_USE("Email is already in use."),
    CUSTOMER_ADDED(" was added as a new customer.");

    private final String message;

    ResponsMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
