package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {
    @Test
    public void createUser_WhenEmailExisted_ReturnFailed() {
        UserDao userDao = mock(UserDao.class);
        UserService userService = new UserServiceImpl(userDao);
        // Define return value for method createUser()
        when(userDao.createUser(anyString())).thenReturn(false);

        // Use mock in test
        assertEquals("FAILED", userService.createUser(anyString(), -7));
    }

    @Test
    void koVoidMethod() {
        UserServiceImpl userServiceFake = new UserServiceImpl();
        UserServiceImpl userService = spy(userServiceFake);
        Animal a = new Animal();
        a.setAge(5);
        when(userService.getA()).thenReturn(a);
        assertEquals(5, userService.koVoidMethod());
    }

    @Test
    void testForFun() {
    }
}