package com.testinium.testdatapersister.client.model;

/**
 * Created by erdoganonur
 * on 24.05.2018.
 */
public class TestData extends AbstractResponse {

    private String key;
    private String value;
    private String scope;
    private String bucket;


    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", scope='" + scope + '\'' +
                ", bucket='" + bucket + '\'' +
                '}';
    }
}
