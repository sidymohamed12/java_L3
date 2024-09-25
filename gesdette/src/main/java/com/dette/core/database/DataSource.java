package com.dette.core.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataSource {
    void connexion();

    String generateSql(String action, String tableName, String[] columns, String condition);

    void setFields(Object... params) throws SQLException;

    int executeUpdate(String query, Object... params);

    int executeUpdate() throws SQLException;

    ResultSet executeQuery(String query) throws SQLException;

    ResultSet executeQuery() throws SQLException;

    public void initPreparedStatement(String sql) throws SQLException;
}
