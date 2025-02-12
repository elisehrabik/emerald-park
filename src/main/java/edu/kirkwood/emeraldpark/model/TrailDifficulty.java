package edu.kirkwood.emeraldpark.model;

public enum TrailDifficulty {
    EASY,
    MODERATE,
    DIFFICULT,
    EXTREME;

    // Used ChatGPT to figure out how to handle the enumeration
    // Convert a string from the database to the corresponding enum
    public static TrailDifficulty fromString(String difficulty) {
        if (difficulty == null || difficulty.isEmpty()) {
            return null;
        }
        try {
            return TrailDifficulty.valueOf(difficulty.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid difficulty value: " + difficulty, e);
        }
    }

    // Convert the enum to a string for the database
    public String toDatabaseString() {
        return this.name().toLowerCase();
    }
}
