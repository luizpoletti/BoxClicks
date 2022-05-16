package com.boxstore.clicks.dao;

import com.boxstore.clicks.data.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {

    @Getter
    private static final List<User> users = new ArrayList<>();

    public static User getUser(UUID uuid) {
        return users.stream().filter(users -> users.getUuid().toString().equals(uuid.toString())).findFirst().orElse(new User(UUID.randomUUID(), 0));
    }

}
