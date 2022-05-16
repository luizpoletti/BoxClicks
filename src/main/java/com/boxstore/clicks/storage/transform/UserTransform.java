package com.boxstore.clicks.storage.transform;

import com.boxstore.clicks.data.user.User;
import lombok.val;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserTransform {

    public User transform(ResultSet resultSet) throws SQLException {
        val uuid = resultSet.getString("uuid");
        val clicks = resultSet.getDouble("clicks");

        return new User(UUID.fromString(uuid), clicks);
    }

}
