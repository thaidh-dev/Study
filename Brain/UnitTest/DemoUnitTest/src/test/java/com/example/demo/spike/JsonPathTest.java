package com.example.demo.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {
    @Test
    public void learning() {
        String responseFromService = "[{\"id\":1,\"name\":\"Áo\",\"price\":10000,\"quantity\":7,\"value\":70000}," +
                "{\"id\":2,\"name\":\"Quần\",\"price\":90000,\"quantity\":8,\"value\":720000}," +
                "{\"id\":3,\"name\":\"Ví\",\"price\":100000,\"quantity\":2,\"value\":200000}," +
                "{\"id\":4,\"name\":\"Đồng hồ\",\"price\":50000,\"quantity\":3,\"value\":150000}," +
                "{\"id\":5,\"name\":\"Laptop\",\"price\":30000,\"quantity\":8,\"value\":240000}," +
                "{\"id\":6,\"name\":\"Nhẫn\",\"price\":80000,\"quantity\":6,\"value\":480000}," +
                "{\"id\":7,\"name\":\"Tất\",\"price\":20000,\"quantity\":9,\"value\":180000}]";

        DocumentContext context = JsonPath.parse(responseFromService);

        int length = context.read("$.length()");
        assertThat(length).isEqualTo(7);

        List<Integer> ids = context.read("$..id");

        assertThat(ids).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7);

        System.out.println(context.read("$[1]").toString());
        System.out.println(context.read("$[0:2]").toString());
        System.out.println(context.read("$[?(@.name=='Áo')]").toString());
        System.out.println(context.read("$[?(@.quantity==8)]").toString());
    }
}
