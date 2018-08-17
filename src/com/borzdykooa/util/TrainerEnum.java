package com.borzdykooa.util;

public enum TrainerEnum {
    TRAINERS("trainers"),
    TRAINER("trainer"),
    NAME("name"),
    LANGUAGE("language"),
    EXPERIENCE("experience");

    private String value;

    TrainerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
