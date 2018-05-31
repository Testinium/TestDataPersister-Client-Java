package com.testinium.testdatapersister.client;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.testinium.testdatapersister.client.base.UniRestClient;
import com.testinium.testdatapersister.client.model.RestResponsePage;
import com.testinium.testdatapersister.client.model.TestData;

/**
 * Created by erdoganonur
 * on 24.05.2018.
 */
public class TestDataClient extends UniRestClient<TestData> {


    private final String testDataPath = "/data";

    public TestDataClient(String baseApiURL) {
        super(baseApiURL);
    }

    public RestResponsePage<TestData> getAll() throws UnirestException {
        return (RestResponsePage<TestData>) this.get(testDataPath);
    }

    public RestResponsePage<TestData> getTestDataByBucket(String bucket) throws UnirestException {
        return (RestResponsePage<TestData>) this.get(testDataPath + "/" + bucket);
    }


    public TestData putTestDataWithBucket(String bucket, TestData testData) throws UnirestException {
        return (TestData) this.put("/" + bucket, testData);
    }
}
