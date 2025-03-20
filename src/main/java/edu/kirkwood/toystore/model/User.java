package edu.kirkwood.toystore.model;

import edu.kirkwood.shared.Validators;

import java.time.Instant;
import java.time.LocalDate;

public class User implements Comparable<User> {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private char[] password;
    private String language;
    private String status;
    private String privileges;
    private Instant createdAt;
    private String timezone;
    private String pronouns;
    private LocalDate birthday = LocalDate.now();
    private String avatar;

    public User() {
    }

    public User(int userId,
                String firstName,
                String lastName,
                String email,
                String phone,
                char[] password,
                String language,
                String status,
                String privileges,
                Instant createdAt,
                String timezone,
                String pronouns,
                LocalDate birthday,
                String avatar
    ) {
        this.avatar = avatar;
        this.birthday = birthday;
        this.pronouns = pronouns;
        this.timezone = timezone;
        this.createdAt = createdAt;
        this.privileges = privileges;
        this.status = status;
        this.language = language;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!Validators.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(!Validators.isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phone = phone;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        if(password != null) {
            String passwordString = String.valueOf(password);
            if(!Validators.isStrongPassword(passwordString)) {
                throw new IllegalArgumentException("Password requires at least 8 characters, and at least 3 of 4: lowercase leter, uppercase letter, number, symbol");
            }
        }
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if(!Validators.isValidLanguage(language)) {
            throw new IllegalArgumentException("Invalid language option");
        }
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        if(!Validators.isValidTimezone(timezone)) {
            throw new IllegalArgumentException("Invalid timezone option");
        }
        this.timezone = timezone;
    }


    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public String getBirthday() {
        return birthday.toString();
    }


    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(User o) {
        int result = this.lastName.compareTo(o.lastName);
        if (result == 0) {
            result = this.firstName.compareTo(o.firstName);
        }
        return 0;
    }
}














