package com.boxstore.clicks.storage.providers;

import com.boxstore.clicks.storage.DBProvider;
import com.boxstore.clicks.utils.TangramUtils;
import lombok.val;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements DBProvider {

    private Connection connection;

    public SQLite() {
        openConnection();
        createTables();
    }

    @Override
    public void openConnection() {
        val file = new File("plugins/BoxClicks/cache/database.db");
        val url = "jdbc:sqlite:" + file;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);

            TangramUtils.sendMessage(Bukkit.getConsoleSender(), "§fConexão §bSQLite §faberta");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() {
        if (connection == null)
            return;

        try {
            connection.close();
            TangramUtils.sendMessage(Bukkit.getConsoleSender(), "§fConexão §bSQLite §ffechada");

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
