package com.app.appcafe.Domain;

public class TimeOrder {
    private int Id;
    private String Value;

    public TimeOrder() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return Value;
    }
}
