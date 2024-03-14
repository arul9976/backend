package com.example.first.Notes;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class UserData {
    @Id
    private String id;

    private String username;

    private String Heading;

    private String Content;

    private String radio;

    public UserData() {

    }

    public UserData(String id, String username, String heading, String content) {
        this.id = id;
        this.username = username;
        this.Heading = heading;
        this.Content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        this.Heading = heading;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "UserData [id=" + id + ", Heading=" + Heading + ", Content=" + Content + "]";
    }

}
