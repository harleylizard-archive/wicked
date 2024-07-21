package com.harleylizard.wicked.common.block;

public enum Wood {
    DOGWOOD("dogwood"),
    HOLLY("holly"),
    LINDEN("linden");

    private final String name;

    Wood(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
