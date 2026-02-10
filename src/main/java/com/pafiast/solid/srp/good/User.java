package com.pafiast.solid.srp.good;

/**
 * Domain entity representing a User.
 * This class is responsible only for holding user data.
 */
public class User {

    private final String name;
    private final String email;

    /**
     * Constructs a new User.
     *
     * @param name  the user's name
     * @param email the user's email
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the user's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the user's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}

