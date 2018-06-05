package com.testinium.testdatapersister.client.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.testinium.testdatapersister.client.TestDataPersisterClient;
import com.testinium.testdatapersister.client.model.Bucket;
import com.testinium.testdatapersister.client.model.RestResponsePage;
import com.testinium.testdatapersister.client.model.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by erdoganonur
 * on 24.05.2018.
 */
@RunWith(JUnit4.class)
public class TestDataClientTest {

    @Test
    public void testDataClientTest() throws UnirestException {

//        TestDataClient testDataClient = Feign.builder()
//                .client(new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new TestDataDecoder())
//                .logger(new Slf4jLogger(TestDataClient.class))
//                .logLevel(Logger.Level.FULL)
//                .target(TestDataClient.class, "http://localhost:8080/data");
//
//        List<TestData> testDataList = testDataClient.getAllTestData();
//
//        for(TestData t : testDataList){
//            System.out.println(t.toString());
//        }

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        HttpResponse<JsonNode> body = Unirest.get("http://localhost:8080/data").asJson();

        HttpResponse<RestResponsePage> testDataPage = Unirest.get("http://localhost:8080/data").asObject(RestResponsePage.class);


        InputStream inputStream = body.getRawBody();

        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(textBuilder);
    }

    @Test
    public void newClientTest() throws UnirestException {

        TestDataPersisterClient testDataPersisterClient = new TestDataPersisterClient("http://localhost:8080");

        RestResponsePage<TestData> response = testDataPersisterClient.getTestDataClient().getAll();
        System.out.println(response.toString());
        System.out.println(response.getContent().toString());

        RestResponsePage<Bucket> bucketList = testDataPersisterClient.getBucketClient().getAll();
        System.out.println(bucketList);
        System.out.println(bucketList.getContent().get(0    ));

    }
}
