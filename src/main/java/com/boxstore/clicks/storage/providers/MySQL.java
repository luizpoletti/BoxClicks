package com.boxstore.clicks.storage.providers;

import com.boxstore.clicks.Main;
import com.boxstore.clicks.storage.DBProvider;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements DBProvider {

    private final FileConfiguration config;

    private Connection connection;

    public MySQL(Main main) {
        config = main.getConfig();

        openConnection();
        createTables();
    }

    @Override
    public void openConnection() {
        val host = config.getString("MySQL.Host");
        val user = config.getString("MySQL.User");
        val db = config.getString("MySQL.DB");
        val password = config.getString("MySQL.Password");
        val url = "jdbc:mysql://" + host + "/" + db + "?autoReconnect=true";

        try {
            connection = DriverManager.getConnection(url, user, password);
            TangramUtils.sendMessage(Bukkit.getConsoleSender(), "§fConexão §bMySQL §faberta");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() {
        if (connection == null)
            return;

        try {
            connection.close();
            TangramUtils.sendMessage(Bukkit.getConsoleSender(), "§fConexão §bMySQL §ffechada");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void executeQuery(String query) {
        try (val preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void executeUpdate(String query, Object... values) {
        try (val preparedStatement = connection.prepareStatement(query)) {
            if (values != null && values.length > 0)
                for (int index = 0; index < values.length; index++)
                    preparedStatement.setObject(index + 1, values[index]);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getValue(String table, String column, Object value, int columnIndex) {
        String valueFinally = null;

        try (val preparedStatement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + column + "='" + value + "'")) {
            val resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                valueFinally = resultSet.getString(columnIndex);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valueFinally;
    }

    @Override
    public void createTables() {
        executeQuery("CREATE TABLE IF NOT EXISTS `box_clicks` (uuid TEXT NOT NULL, clicks DOUBLE NOT NULL)");
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
