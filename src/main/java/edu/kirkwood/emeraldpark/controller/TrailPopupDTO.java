package edu.kirkwood.emeraldpark.controller;

import edu.kirkwood.emeraldpark.model.Trail;

public class TrailPopupDTO {
    private int trail_id;
    private String trail_name;
    private String trail_description;
    private double trail_distance;
    private String trail_difficulty;
    private String trail_image;
    private String categoryName;

    public TrailPopupDTO(Trail trail) {
        this.trail_id = trail.getTrail_id();
        this.trail_name = trail.getTrail_name();
        this.trail_description = trail.getTrail_description();
        this.trail_distance = trail.getTrail_distance();
        this.trail_difficulty = trail.getTrail_difficulty().toString();
        this.trail_image = trail.getTrail_image();
        this.categoryName = trail.getCategoryName();
    }

    public int getTrail_id() {
        return trail_id;
    }

    public String getTrail_name() {
        return trail_name;
    }

    public String getTrail_description() {
        return trail_description;
    }

    public double getTrail_distance() {
        return trail_distance;
    }

    public String getTrail_difficulty() {
        return trail_difficulty;
    }

    public String getTrail_image() {
        return trail_image;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
