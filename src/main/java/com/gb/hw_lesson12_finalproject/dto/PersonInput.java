package com.gb.hw_lesson12_finalproject.dto;

import javax.validation.constraints.NotBlank;

public class PersonInput {
    private String firstname;
    private String lastname;
    private String city;
    @NotBlank
    private String login;
    private String password;


//    Нужен ли в DTO объекте конструктор?


    public PersonInput(String firstname, String lastname, String city, @NotBlank  String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.login = login;
        this.password = password;
    }

    public PersonInput() {
    }

    @Override
    public String toString() {
        return "PersonInput{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
