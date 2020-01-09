package com.falcon.warehouse.entity;

import androidx.annotation.NonNull;

public class Skeleton {

    private String name;

    private int age;

    public Skeleton() {
    }

    public Skeleton(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
