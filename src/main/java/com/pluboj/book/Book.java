package com.pluboj.book;

import com.pluboj.core.BaseEntity;
import com.pluboj.review.Review;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book extends BaseEntity{
    @NotNull
    @Size(min = 2, max = 140)
    private String title;
    @NotNull
    @Size(min = 2, max = 140)
    private String author;
    private String published;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    protected Book() {
        super();
        reviews = new ArrayList<>();
    }

    public Book(String title, String author, String published) {
        this();
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        review.setBook(this);
        reviews.add(review);
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
