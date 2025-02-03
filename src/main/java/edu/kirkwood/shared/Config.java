package edu.kirkwood.shared;

import java.util.Optional;

public class Config {
    public static String getEnv(String key) {
        return Optional.ofNullable(System.getenv(key))
                .orElseThrow(() -> new IllegalStateException("Environment variable " + key + " is not set"));
    }
}
