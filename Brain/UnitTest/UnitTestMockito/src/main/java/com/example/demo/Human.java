package com.example.demo;

public class Human {
    private String name;
    private int age;
    private Animal animal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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

    public int calWeight(int age) {
        switch (age) {
            case 1:
                return 100;
            case 2:
                return 200;
            default:
                return 700;
        }
    }

    public int buyPet(int VAT) {
        int agePet = animal.getAge();
        switch (agePet + VAT) {
            case 1:
                return 100;
            case 2:
                return 200;
            default:
                return 700;
        }
    }

    public String outfitSize(int age) {
        int weight = calWeight(age);
        switch (weight) {
            case 100:
                return "M";
            case 200:
                return "L";
            default:
                return "XL";
        }
    }
}
