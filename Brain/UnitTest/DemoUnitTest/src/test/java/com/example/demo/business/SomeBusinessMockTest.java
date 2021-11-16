package com.example.demo.business;

import com.example.demo.data.SomeDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTest {
    @InjectMocks
    SomeBusinessImpl business;
    @Mock
    SomeDataService dataServiceMock;

//    SomeBusinessImpl business = new SomeBusinessImpl();
//    SomeDataService dataServiceMock = mock(SomeDataService.class);

//    @Before
//    public void before() {
//        business.setSomeDataService(dataServiceMock);
//    }

    @Test
    public void calculateSumUsingDataService_basic() {
        // set giá trị trả về của 1 hàm
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});
        assertEquals(6, business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataService_oneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});
        assertEquals(5, business.calculateSumUsingDataService());
    }
}
