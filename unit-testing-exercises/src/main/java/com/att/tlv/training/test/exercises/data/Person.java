package com.att.tlv.training.test.exercises.data;

public class Person {

    private final long id;
    private final String name;

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
