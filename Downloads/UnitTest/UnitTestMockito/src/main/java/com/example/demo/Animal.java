package com.example.demo;

public class Animal {
    private String name;
    private int age;
    private boolean isWing;

    public Animal() {
    }

    public Animal(String name, int age, boolean isWing) {
        this.name = name;
        this.age = age;
        this.isWing = isWing;
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

    public boolean isWing() {
        return isWing;
    }

    public void setWing(boolean wing) {
        isWing = wing;
    }

    public int speed(String name) {
        switch (name) {
            case "1":
                return 100;
            case "2":
                return 200;
            default:
                return 700;
        }

    }
}
