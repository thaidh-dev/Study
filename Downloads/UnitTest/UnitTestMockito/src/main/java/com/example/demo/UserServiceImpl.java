package com.example.demo;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    private Animal animal;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }

    @Override
    public String createUser(String email, int cal) {
        int thaidh = cal + 1;
        if (thaidh > 0) {
            return "> 0";
        }

        boolean result = userDao.createUser(email);
        if (result) {
            // Send an email verify ...
            // Show a success message to end user ...
            return "TRUE";
        }
        // Send an error message to end user ...
        return "FAILED";
    }

    public int koVoidMethod() {
        Animal a = getA();
        return a.getAge();
    }

    public Animal getA() {
        Animal animal = new Animal();
        animal.setAge(7);
        animal.setName("xxx");
        return animal;
    }
}
