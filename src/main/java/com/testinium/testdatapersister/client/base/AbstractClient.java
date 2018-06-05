package com.testinium.testdatapersister.client.base;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.testinium.testdatapersister.client.enums.AuthType;
import com.testinium.testdatapersister.client.model.AbstractResponse;
import com.testinium.testdatapersister.client.model.BasicAuthModel;

/**
 * Created by erdoganonur
 * on 28.05.2018.
 */
public abstract class AbstractClient<TD extends AbstractResponse> {

    protected String baseURL;
    protected BasicAuthModel basicAuth;
    protected AuthType authType = AuthType.BASIC;


    public AbstractClient(String baseURL) {
        this.baseURL = baseURL;
        this.basicAuth = new BasicAuthModel("admin", "admin");
    }

    public AbstractClient(String baseURL, BasicAuthModel basicAuth) {
        this.baseURL = baseURL;
        this.basicAuth = basicAuth;
    }

    /**
     * This is a generic http get method without an implementation
     *
     * @param path
     * @return
     * @throws UnirestException
     */
    public abstract AbstractResponse<TD> get(String path) throws UnirestException;

    /**
     * This is a generic http put method without an implementation
     *
     * @param path
     * @param data
     * @return
     * @throws UnirestException
     */
    public abstract AbstractResponse<TD> put(String path, TD data) throws UnirestException;

    /**
     * This is a generic http post method without an implementation
     *
     * @param path
     * @param data
     * @return
     * @throws UnirestException
     */
    public abstract AbstractResponse<TD> post(String path, TD data) throws UnirestException;

    /**
     * This is a generic http delete method without an implementation
     *
     * @param path
     * @param data
     * @return
     * @throws UnirestException
     */
    public abstract AbstractResponse<TD> delete(String path, TD data) throws UnirestException;


    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public BasicAuthModel getBasicAuth() {
        return basicAuth;
    }

    public void setBasicAuth(BasicAuthModel basicAuth) {
        this.basicAuth = basicAuth;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }
}
