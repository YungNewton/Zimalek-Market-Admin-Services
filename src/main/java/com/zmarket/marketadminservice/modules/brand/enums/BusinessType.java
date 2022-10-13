package com.zmarket.marketadminservice.modules.brand.enums;

public enum BusinessType {
    RC("Limited Company"),
    BS("Business");

    private final String value;

    BusinessType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
