package com.example.demo.controller;

import com.example.demo.data.ItemRepository;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// tự set file cấu hình, nếu file đấy có tên 'application.properties' thì không cần
@TestPropertySource(locations = {"classpath:test-application.properties"})
public class ItemControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws JSONException {
        String response  = restTemplate.getForObject("/all-items-from-database", String.class);
        JSONAssert.assertEquals("[{id:1}, {id:2}, {id:3}, {id:4}, {id:5}, {id:6}, {id:7}]", response, false);
    }
}
