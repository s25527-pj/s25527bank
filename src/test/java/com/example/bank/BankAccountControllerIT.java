package com.example.bank;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.http.HttpHeaders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = S25527bankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankAccountControllerIT {
//    @LocalServerPort
//    private int port;
//
//    TestRestTemplate restTemplate = new TestRestTemplate();
//
//    HttpHeaders headers = new HttpHeaders();
//
//    @Test
//    public void testGetBankAccounts() throws JSONException {
//
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/bank/accounts"),
//                HttpMethod.GET, entity, String.class);
//
//        String expected;
//        JSONAssert.assertEquals(expected, response.getBody(), false);
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }
}
