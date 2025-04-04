package edu.kirkwood.emeraldpark.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Review {
    private int review_id;
    private int trail_id;
    private String trail_name;
    private int user_id;
    private String first_name;
    private String last_name;
    private LocalDate review_date;
    private int rating;
    private String review_notes;

    public Review() {}

    public Review(int review_id, int trail_id, String trail_name, int user_id, String first_name, String last_name, LocalDate review_date, int rating, String review_notes) {
        this.review_id = review_id;
        this.trail_id = trail_id;
        this.trail_name = trail_name;
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.review_date = review_date;
        this.rating = rating;
        this.review_notes = review_notes;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getTrail_id() {
        return trail_id;
    }

    public void setTrail_id(int trail_id) {
        this.trail_id = trail_id;
    }

    public String getTrail_name() {
        return trail_name;
    }

    public void setTrail_name(String trail_name) {
        this.trail_name = trail_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getReview_notes() {
        return review_notes;
    }

    public void setReview_notes(String review_notes) {
        this.review_notes = review_notes;
    }

    public Date getReview_dateAsDate() {
        return review_date != null ? Date.from(review_date.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_id=" + review_id +
                ", trail_id=" + trail_id +
                ", trail_name='" + trail_name + '\'' +
                ", user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", review_date=" + review_date +
                ", rating=" + rating +
                ", review_notes='" + review_notes + '\'' +
                '}';
    }
}