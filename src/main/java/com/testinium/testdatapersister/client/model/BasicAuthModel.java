package com.testinium.testdatapersister.client.model;

import java.util.Objects;

/**
 * Created by erdoganonur
 * on 1.06.2018.
 */
public class BasicAuthModel {

    private String username;
    private String password;

    public BasicAuthModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicAuthModel that = (BasicAuthModel) o;
        return Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "BasicAuthModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
