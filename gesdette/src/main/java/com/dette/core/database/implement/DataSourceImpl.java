package com.dette.core.database.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dette.core.database.DataSource;

public class DataSourceImpl implements DataSource {
    protected final String url = "jdbc:mysql://localhost:3306/gesdette";
    protected final String user = "root";
    protected final String mdp = "Mohamed2709";
    protected PreparedStatement ps;
    protected Connection conn = null;

    @Override
    public void connexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, mdp);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Échec de la connexion à la BD: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ----------------------- GENERATEUR DE REQUETE --------------------
    @Override
    public String generateSql(String action, String tableName, String[] columns, String condition) {
        StringBuilder sql = new StringBuilder();

        if ("INSERT".equalsIgnoreCase(action)) {
            sql.append("INSERT INTO ").append(tableName).append(" (");

            // Ajouter les colonnes
            for (int i = 0; i < columns.length; i++) {
                sql.append(columns[i]);
                if (i < columns.length - 1) {
                    sql.append(", ");
                }
            }

            sql.append(") VALUES (");

            // Ajouter les placeholders pour les valeurs (?)
            for (int i = 0; i < columns.length; i++) {
                sql.append("?");
                if (i < columns.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(")");

        } else if ("UPDATE".equalsIgnoreCase(action)) {
            sql.append("UPDATE ").append(tableName).append(" SET ");

            // Ajouter les colonnes avec leurs placeholders
            for (int i = 0; i < columns.length; i++) {
                sql.append(columns[i]).append(" = ?");
                if (i < columns.length - 1) {
                    sql.append(", ");
                }
            }

            // Ajouter la condition WHERE si fournie
            if (condition != null && !condition.isEmpty()) {
                sql.append(" WHERE ").append(condition);
            }

        } else if ("SELECT".equalsIgnoreCase(action)) {
            sql.append("SELECT ");

            // Ajouter les colonnes (ou * si aucun colonne spécifiée)
            if (columns == null || columns.length == 0) {
                sql.append("*");
            } else {
                for (int i = 0; i < columns.length; i++) {
                    sql.append(columns[i]);
                    if (i < columns.length - 1) {
                        sql.append(", ");
                    }
                }
            }

            sql.append(" FROM ").append(tableName);

            // Ajouter la condition WHERE si fournie
            if (condition != null && !condition.isEmpty()) {
                sql.append(" WHERE ").append(condition);
            }

        }

        return sql.toString();
    }

    // --------------------- generateur de set ---------------

    @Override
    public void setFields(Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof String) {
                ps.setString(i + 1, (String) params[i]);
            } else if (params[i] instanceof Integer) {
                ps.setInt(i + 1, (Integer) params[i]);
            } else if (params[i] instanceof Double) {
                ps.setDouble(i + 1, (Double) params[i]);
            }
            // Ajouter d'autres types de données si nécessaire
        }
    }

    // ---------------------------- EXECUTE UPDATE --------------------------
    @Override
    public int executeUpdate(String query, Object... params) {
        int lignes = 0;
        try {
            connexion();
            initPreparedStatement(query);
            setFields(params);
            lignes = this.ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " +
                    e.getMessage());
        }
        return lignes;
    }

    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

    // ---------------------------- EXECUTE QUERY --------------------------
    @Override
    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            connexion();
            initPreparedStatement(query);
            rs = this.ps.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " +
                    e.getMessage());
        }
        return rs;
    }

    // ---- 2
    public ResultSet executeQuery() throws SQLException {
        return ps.executeQuery();
    }

    @Override
    public void initPreparedStatement(String sql) throws SQLException {
        if (sql.toUpperCase().trim().startsWith("INSERT")) {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            ps = conn.prepareStatement(sql);
        }
    }

}
