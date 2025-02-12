package edu.kirkwood.emeraldpark.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Review {
    private int review_id;
    private String trail_name;
    private String visitor_email;
    private LocalDate review_date;
    private int rating;
    private LocalTime review_time;
    private String review_notes;

    public Review() {

    }

    public Review(int review_id, String trail_name, String visitor_email, LocalDate review_date, int rating, LocalTime review_time, String review_notes) {
        this.review_id = review_id;
        this.trail_name = trail_name;
        this.visitor_email = visitor_email;
        this.review_date = review_date;
        this.rating = rating;
        this.review_time = review_time;
        this.review_notes = review_notes;
    }

    public String getTrail_name() {
        return trail_name;
    }

    public void setTrail_name(String trail_name) {
        this.trail_name = trail_name;
    }

    public String getVisitor_email() {
        return visitor_email;
    }

    public void setVisitor_email(String visitor_email) {
        this.visitor_email = visitor_email;
    }

    public LocalDate getReview_date() {
        return review_date;
    }

    public void setReview_date(LocalDate review_date) {
        this.review_date = review_date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalTime getReview_time() {
        return review_time;
    }

    public void setReview_time(LocalTime review_time) {
        this.review_time = review_time;
    }

    public String getReview_notes() {
        return review_notes;
    }

    public void setReview_notes(String review_notes) {
        this.review_notes = review_notes;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_id=" + review_id +
                ", trail_name='" + trail_name + '\'' +
                ", visitor_email='" + visitor_email + '\'' +
                ", review_date=" + review_date +
                ", rating=" + rating +
                ", review_time=" + review_time +
                ", review_notes='" + review_notes + '\'' +
                '}';
    }
}
