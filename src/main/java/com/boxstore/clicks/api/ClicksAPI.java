package com.boxstore.clicks.api;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.dao.UserDAO;
import com.boxstore.clicks.data.user.User;
import com.boxstore.clicks.storage.DBProvider;
import com.boxstore.clicks.storage.transform.UserTransform;
import lombok.val;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClicksAPI {

    private final DBProvider db;

    public ClicksAPI(Main main) {
        db = main.getDbProvider();
    }

    public void createAccount(UUID uuid) {
        val user = new User(uuid, 0);

        try (val preparedStatement = db.getConnection().prepareStatement("INSERT INTO `box_clicks` (uuid, clicks) VALUES(?,?)")) {
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setDouble(2, 0.0);
            preparedStatement.execute();

            UserDAO.getUsers().add(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean hasAccount(UUID uuid) {
        return db.getValue("box_clicks", "uuid", uuid.toString(), 1) != null;
    }

    public User getUser(UUID uuid) {
        return UserDAO.getUser(uuid);
    }

    public double getClicks(UUID uuid) {
        return getUser(uuid).getClicks();
    }

    public void addClicks(UUID uuid, double amount) {
        if (!hasAccount(uuid))
            createAccount(uuid);

        val user = getUser(uuid);
        user.addClicks(amount);
    }

    public void setClicks(UUID uuid, double amount) {
        if (!hasAccount(uuid))
            createAccount(uuid);

        val user = getUser(uuid);
        user.setClicks(amount);
    }

    public void removeClicks(UUID uuid, double amount) {
        if (!hasAccount(uuid))
            createAccount(uuid);

        val user = getUser(uuid);
        user.removeClicks(amount);
    }

    public boolean hasClicks(UUID uuid, double amount) {
        return getClicks(uuid) >= amount;
    }

    public List<User> findAll() {
        List<User> values = new ArrayList<>();

        try (val preparedStatement = db.getConnection().prepareStatement("SELECT * FROM `box_clicks`")) {
            val resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                val value = new UserTransform().transform(resultSet);
                if (value == null)
                    continue;

                values.add(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    public void save(User user) {
        db.executeUpdate("UPDATE `box_clicks` SET clicks = ? WHERE uuid = ?", user.getClicks(), user.getUuid());
    }

}
