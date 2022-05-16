package com.boxstore.clicks.storage;


import java.sql.Connection;

public interface DBProvider {

    void openConnection();

    void closeConnection();

    void executeQuery(String query);

    void executeUpdate(String query, Object... values);

    String getValue(String table, String column, Object value, int columnIndex);

    void createTables();

    Connection getConnection();

}
