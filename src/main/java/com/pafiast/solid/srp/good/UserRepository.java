package com.pafiast.solid.srp.good;

/**
 * Repository interface for User persistence.
 * Responsible solely for database operations related to users.
 */
public interface UserRepository {

    /**
     * Saves a user to the data store.
     *
     * @param user the user to save
     */
    void save(User user);
}

