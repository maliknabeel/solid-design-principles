package com.example.solid.srp.good;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }
}

