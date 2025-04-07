package edu.kirkwood.emeraldpark.model;

import java.sql.Time;
import java.time.LocalTime;

public class Trail {
    int trail_id;
    String trail_name;
    double trail_distance;
    TrailDifficulty trail_difficulty;
    LocalTime trail_time;
    String trail_description;
    String trail_image;
    private int categoryId;
    private String categoryName;

    public Trail(int trail_id, String trail_name, double trail_distance, TrailDifficulty trail_difficulty, LocalTime trail_time, String trail_description, String trail_image , int categoryId, String categoryName) {
        this.trail_id = trail_id;
        this.trail_name = trail_name;
        this.trail_distance = trail_distance;
        this.trail_difficulty = trail_difficulty;
        this.trail_time = trail_time;
        this.trail_description = trail_description;
        this.trail_image = trail_image;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Trail() {

    }

    public Trail(int id, String trailName, double trailDistance, TrailDifficulty trailDifficulty, LocalTime trailTime, String trailDescription, String trailImage, int categoryId) {
    }

    public String getTrail_name() {
        return trail_name;
    }

    public void setTrail_name(String trail_name) {
        this.trail_name = trail_name;
    }

    public double getTrail_distance() {
        return trail_distance;
    }

    public void setTrail_distance(double trail_distance) {
        this.trail_distance = trail_distance;
    }

    public TrailDifficulty getTrail_difficulty() {
        return trail_difficulty;
    }

    public void setTrail_difficulty(TrailDifficulty trail_difficulty) {
        this.trail_difficulty = trail_difficulty;
    }

    public LocalTime getTrail_time() {
        return trail_time;
    }

    public void setTrail_time(LocalTime trail_time) {
        this.trail_time = trail_time;
    }

    public String getTrail_timeFormatted() {
        if (trail_time != null) {
            long minutes = trail_time.toSecondOfDay() / 60;
            long hours = minutes / 60;
            long remainingMinutes = minutes % 60;

            if (hours == 0) {
                return String.format("%d minutes", remainingMinutes);
            } else {
                return String.format("%d hr %d min", hours, remainingMinutes);
            }
        }
        return "Unknown";
    }

    public String getTrail_description() {
        return trail_description;
    }

    public void setTrail_description(String trail_description) {
        this.trail_description = trail_description;
    }

    public String getTrail_image() {
        return trail_image;
    }

    public void setTrail_image(String trail_image) {
        this.trail_image = trail_image;
    }

    public int getTrail_id() {
        return trail_id;
    }

    public void setTrail_id(int trail_id) {
        this.trail_id = trail_id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Trail{" +
                "trail_id=" + trail_id +
                ", trail_name='" + trail_name + '\'' +
                ", trail_distance=" + trail_distance +
                ", trail_difficulty=" + trail_difficulty +
                ", trail_time=" + trail_time +
                ", trail_description='" + trail_description + '\'' +
                ", trail_image='" + trail_image + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
