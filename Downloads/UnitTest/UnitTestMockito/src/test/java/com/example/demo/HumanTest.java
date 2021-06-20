package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

class HumanTest {
    @Test
    void one() {
        Human human = mock(Human.class);
        when(human.calWeight(anyInt())).thenReturn(2);

        assertEquals(2, human.calWeight(1));
    }

    @Test
    void two() {
        Animal animal = mock(Animal.class);
        when(animal.getAge()).thenReturn(2);
        Human human = new Human();
        human.setAnimal(animal);
        int price = human.buyPet(0);
        assertEquals(price, 200);
    }

    @Test
    void three() {
        Human human = new Human();
        human = spy(human);
        when(human.calWeight(anyInt())).thenReturn(200);
        String size = human.outfitSize(1);
        assertEquals("L", size);
    }
}