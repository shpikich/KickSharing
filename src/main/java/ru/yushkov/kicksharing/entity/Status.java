package ru.yushkov.kicksharing.entity;

public enum Status {
    AVAILABLE("AVAILABLE"),
    RENTED("RENTED"),
    ALL("ALL");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
