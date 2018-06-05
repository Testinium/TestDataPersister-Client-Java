package com.testinium.testdatapersister.client.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.testinium.testdatapersister.client.model.AbstractResponse;
import com.testinium.testdatapersister.client.model.RestResponsePage;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by erdoganonur
 * on 28.05.2018.
 */
public class UniRestClient<TD> extends AbstractClient {


    public UniRestClient(String baseApiURL) {
        super(baseApiURL);
        this.initUnirest();
    }

    /**
     * Defination of object mapper json
     */
    private void initUnirest() {

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    if (isPageable(value)) {
                        return (T) jacksonObjectMapper.readValue(value, RestResponsePage.class);
                    } else {
                        return jacksonObjectMapper.readValue(value, valueType);
                    }
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
    }

    public AbstractResponse<TD> get(String path) throws UnirestException {
        HttpResponse<AbstractResponse> response = Unirest.get(this.baseURL + path).basicAuth(this.basicAuth.getUsername(), this.basicAuth.getPassword()).asObject(AbstractResponse.class);
        return response.getBody();
    }


    public AbstractResponse<TD> put(String path, AbstractResponse data) throws UnirestException {

        HttpResponse<AbstractResponse> response = Unirest.put(this.baseURL + path).basicAuth(this.basicAuth.getUsername(), this.basicAuth.getPassword()).body(data).asObject(AbstractResponse.class);
        return response.getBody();
    }


    public AbstractResponse<TD> post(String path, AbstractResponse data) throws UnirestException {

        HttpResponse<AbstractResponse> response = Unirest.post(this.baseURL + path).basicAuth(this.basicAuth.getUsername(), this.basicAuth.getPassword()).body(data).asObject(AbstractResponse.class);
        return response.getBody();
    }

    public AbstractResponse<TD> delete(String path, AbstractResponse data) throws UnirestException {

        HttpResponse<AbstractResponse> response = Unirest.delete(this.baseURL + path).basicAuth(this.basicAuth.getUsername(), this.basicAuth.getPassword()).body(data).asObject(AbstractResponse.class);
        return response.getBody();
    }

    private boolean isPageable(String value) throws IOException {


        com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

        boolean isPageable = false;

//        Field[] fields = RestResponsePage.class.getDeclaredFields();
//        for (Field f : fields) {
//            if (!jacksonObjectMapper.readTree(value).has(f.getName()))
//                isPageable = false;
//        }

        if (jacksonObjectMapper.readTree(value).has("content") && jacksonObjectMapper.readTree(value).has("number"))
            isPageable = true;

        return isPageable;
    }
}
