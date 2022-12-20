package com.example.assignmentlogintulassi.sqlite;

public class Data {
    private String title;
    private String user;
    private String email;
    private String phone;
    private String password;

    public Data(String user, String email, String phone, String password) {
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Data(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
