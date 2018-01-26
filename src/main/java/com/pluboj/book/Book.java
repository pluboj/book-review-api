package com.pluboj.book;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.pluboj.core.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Book extends BaseEntity{
    private String title;
    private String author;
    private String published;

    protected Book() {
        super();
    }

    public Book(String title, String author, String published) {
        this();
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }
}
