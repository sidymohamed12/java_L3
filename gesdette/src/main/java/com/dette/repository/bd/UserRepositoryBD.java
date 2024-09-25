package com.dette.repository.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.dette.core.RepositoryBDImpl;
import com.dette.entities.Client;
import com.dette.entities.User;
import com.dette.enums.EtatUser;
import com.dette.enums.Role;
import com.dette.repository.implement.UserRepository;

public class UserRepositoryBD extends RepositoryBDImpl<User> implements UserRepository {

    public UserRepositoryBD() {
        tableName = "users";
    }

    @Override
    public void insert(User value) {
        try {
            String query = String.format(
                    "INSERT INTO %s (email, login, password, roleId, etatId) VALUES (?, ?, ?, ?, ?)", tableName);
            connexion();
            initPreparedStatement(query);
            ps.setString(1, value.getEmail());
            ps.setString(2, value.getLogin());
            String hashedPassword = BCrypt.hashpw(value.getPassword(), BCrypt.gensalt());
            ps.setString(3, hashedPassword);
            ps.setInt(4, value.getRole().ordinal() + 1);
            ps.setInt(5, value.getEtat().ordinal() + 1);
            executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                value.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM %s", tableName);
            connexion();
            initPreparedStatement(sql);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setId(res.getInt("id"));
                user.setEmail(res.getString("email"));
                user.setLogin(res.getString("login"));
                user.setPassword(res.getString("password"));
                user.setRole(Role.getRoleById(res.getInt("roleId")));
                user.setEtat(EtatUser.getEtatById(res.getInt("etatId")));
                users.add(user);
                // users.add(converToObjet(res, User.class));
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return users;
    }

    @Override
    public User selectBy(String name) {
        User user = null;
        try {
            String sql = String.format("SELECT * FROM %s WHERE login = ?", tableName);
            connexion();
            initPreparedStatement(sql);
            ps.setString(1, name);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                user = new User();
                user.setId(res.getInt("id"));
                user.setEmail(res.getString("email"));
                user.setLogin(res.getString("login"));
                user.setPassword(res.getString("password"));
                user.setRole(Role.getRoleById(res.getInt("roleId")));
                user.setEtat(EtatUser.getEtatById(res.getInt("etatId")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return user;
    }

    @Override
    public int count() {
        int count = 0;
        try {
            String sql = String.format("SELECT id FROM %s ORDER BY id DESC LIMIT 1", tableName);
            connexion();
            initPreparedStatement(sql);
            ResultSet res = ps.executeQuery();

            if (res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return count;
    }

    @Override
    public void update(User value) {
        try {
            String query = String.format(
                    "UPDATE %s SET email = ?, login = ?, password = ?, roleId = ?, etatId = ?  WHERE id = ?",
                    tableName);
            connexion();
            initPreparedStatement(query);
            ps.setString(1, value.getEmail());
            ps.setString(2, value.getLogin());
            String hashedPassword = BCrypt.hashpw(value.getPassword(), BCrypt.gensalt());
            ps.setString(3, hashedPassword);
            ps.setInt(4, value.getRole().ordinal() + 1);
            ps.setInt(5, value.getEtat().ordinal() + 1);
            ps.setInt(6, value.getId());
            executeUpdate();

            // Print SQL statement for debugging
            // System.out.println("Executing SQL: " + statement.toString());
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
    }

    // @Override
    // public Client converToObjet(ResultSet rs) throws SQLException {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'converToObjet'");
    // }

    @Override
    public User selectById(int id) {
        User user = null;
        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);
            connexion();
            initPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                user = new User();
                user.setId(res.getInt("id"));
                user.setEmail(res.getString("email"));
                user.setLogin(res.getString("login"));
                user.setPassword(res.getString("password"));
                user.setRole(Role.getRoleById(res.getInt("roleId")));
                user.setEtat(EtatUser.getEtatById(res.getInt("etatId")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return user;
    }

    @Override
    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    @Override
    public Client converToObjet(ResultSet rs) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'converToObjet'");
    }

}
