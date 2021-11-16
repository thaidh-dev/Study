package com.example.demo.business;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {
    List<String> mock = mock(List.class);
    
    // hàm này là vd tự mò được
    @Test
    public void thaidh() {
        //using mock
        mock.add("once");

        mock.add("twice");
        mock.add("twice");

        mock.add("three times");
        mock.add("three times");
        mock.add("three times");

        mock.add("alo alo");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mock).add("once");
        verify(mock, times(1)).add("once"); // hàm add("once") được gọi 1 lần

        //exact number of invocations verification
        verify(mock, times(2)).add("twice"); // hàm add("twice") xuất hiện 2 lần
        verify(mock, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mock, never()).add("chưa từng xảy ra");

        //verification using atLeast()/atMost()
        verify(mock, atMostOnce()).add("once"); // nhiều nhất 1
        verify(mock, atLeastOnce()).add("alo alo"); // ít nhất 1
        verify(mock, atLeast(2)).add("twice"); // ít nhất 2
        verify(mock, atMost(5)).add("three times"); // nhiều nhất 5
    }

    @Test
    public void size_basic() {
        when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @Test
    public void returnDifferentValues() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());
    }

    @Test
    public void returnWithParameters() {
        when(mock.get(0)).thenReturn("in28Minutes");
        assertEquals("in28Minutes", mock.get(0));
        assertEquals(null, mock.get(1));
    }

    @Test
    public void returnWithGenericParameters() {
        when(mock.get(anyInt())).thenReturn("in28Minutes");
        assertEquals("in28Minutes", mock.get(0));
        assertEquals("in28Minutes", mock.get(1));
    }

    @Test
    public void verificationBasics() {
        String value1 = mock.get(0);
        String value2 = mock.get(1);

        verify(mock).get(0);
        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(anyInt());
        verify(mock, atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());
        verify(mock, never()).get(2);
    }

    @Test
    public void argumentCapturing() {
        mock.add("SomeString");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());

        assertEquals("SomeString", captor.getValue());
    }

    @Test
    public void multipleArgumentCapturing() {
        mock.add("SomeString1");
        mock.add("SomeString2");
        mock.add("SomeString3");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock, times(3)).add(captor.capture());

        List<String> allValues = captor.getAllValues(); // tự dựng lại có tất cả giá trị mà đã add vào mock

        assertEquals("SomeString1", allValues.get(0));
        assertEquals("SomeString2", allValues.get(1));
        assertEquals("SomeString3", allValues.get(2));
    }

    @Test
    public void mocking() {
        ArrayList arrayListMock = mock(ArrayList.class);
        System.out.println(arrayListMock.get(0));
        System.out.println(arrayListMock.size());
        arrayListMock.add("Test");
        arrayListMock.add("Test2");
        System.out.println(arrayListMock.size());
        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size());
    }

    @Test
    public void spying() {
        ArrayList arrayListSpy = spy(ArrayList.class);
        arrayListSpy.add("Test0");
        System.out.println(arrayListSpy.get(0));
        System.out.println(arrayListSpy.size());
        arrayListSpy.add("Test");
        arrayListSpy.add("Test2");
        System.out.println(arrayListSpy.size());
        when(arrayListSpy.size()).thenReturn(5);
        System.out.println(arrayListSpy.size());

        arrayListSpy.add("Test4");
        System.out.println(arrayListSpy.size());

        verify(arrayListSpy).add("Test2");
    }
}
