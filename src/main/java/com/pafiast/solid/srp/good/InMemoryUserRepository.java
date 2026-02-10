package com.pafiast.solid.srp.good;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory implementation of {@link UserRepository}.
 * Stores users in a list for demonstration purposes.
 */
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        users.add(user);
    }
}

