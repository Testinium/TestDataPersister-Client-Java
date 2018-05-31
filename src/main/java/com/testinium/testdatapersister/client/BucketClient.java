package com.testinium.testdatapersister.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.testinium.testdatapersister.client.base.UniRestClient;
import com.testinium.testdatapersister.client.model.AbstractResponse;
import com.testinium.testdatapersister.client.model.Bucket;
import com.testinium.testdatapersister.client.model.RestResponsePage;

import java.util.List;

/**
 * Created by erdoganonur
 * on 28.05.2018.
 */
public class BucketClient extends UniRestClient<Bucket> {


    private String bucketPath = "/bucket";

    public BucketClient(String apiURL) {
        super(apiURL);
    }

    public List<Bucket> getAll() throws UnirestException {
        return (List<Bucket>) this.get(bucketPath);
    }


}
