package com.testinium.testdatapersister.client;

import com.testinium.testdatapersister.client.base.UniRestClient;

/**
 * Created by erdoganonur
 * on 28.05.2018.
 */
public class TestDataPersisterClient {

    private String apiURL;
    private TestDataClient testDataClient;
    private BucketClient bucketClient;

    public TestDataPersisterClient(String apiURL) {
        testDataClient = new TestDataClient(apiURL);
        bucketClient = new BucketClient(apiURL);
    }

    public TestDataClient getTestDataClient() {
        return testDataClient;
    }

    public BucketClient getBucketClient() {
        return bucketClient;
    }


}
