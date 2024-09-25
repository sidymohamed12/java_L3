package com.dette.repository.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dette.core.RepositoryBDImpl;
import com.dette.entities.Client;
import com.dette.entities.User;
import com.dette.repository.implement.ClientRepository;
import com.dette.repository.implement.UserRepository;

public class ClientRepositoryBD extends RepositoryBDImpl<Client> implements ClientRepository {
    UserRepository userRepository;
    private String[] colone = { "id", "surnom", "telephone", "adresse", "userId" };

    public ClientRepositoryBD(UserRepository userRepository) {
        tableName = "client";
        this.userRepository = userRepository;
    }

    @Override
    public void insert(Client value) {

        try {
            String query = String.format("INSERT INTO %s (surnom, telephone, adresse) VALUES (?, ?, ?)", tableName);
            connexion();
            initPreparedStatement(query);

            // Utilisez la méthode setFields pour définir les paramètres
            setFields(value.getSurnom(), value.getTelephone(), value.getAdresse());

            // Exécutez la mise à jour et récupérez les clés générées
            int lignes = executeUpdate(query, value.getSurnom(), value.getTelephone(), value.getAdresse());
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                value.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }

    }

    @Override
    public List<Client> selectAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String query = generateSql("SELECT", tableName, colone, null);
            connexion();
            ResultSet res = executeQuery(query);
            while (res.next()) {
                clients.add(converToObjet(res));
            }
            res.close();
        } catch (

        SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return clients;
    }

    @Override
    public int count() {
        int count = 0;
        try {
            String query = String.format("SELECT id FROM %s ORDER BY id DESC LIMIT 1", tableName);
            connexion();
            initPreparedStatement(query);
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
    public Client selectBy(String name) {
        Client client = null;
        try {
            String query = generateSql("SELECT", tableName, colone, "telephone = ?");
            connexion();
            initPreparedStatement(query);
            ps.setString(1, name);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                client = new Client();
                client.setId(res.getInt("id"));
                client.setSurnom(res.getString("surnom"));
                client.setTelephone(res.getString("telephone"));
                client.setAdresse(res.getString("adresse"));
                // etudiant.setChambre(chambreRepositoryBD.selectById(res.getInt("chambreId")));
            }
            res.close();
        } catch (

        SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return client;
    }

    @Override
    public void update(Client client) {
        try {
            String query = String
                    .format("UPDATE %s SET surnom = ?, telephone = ?, adresse = ?, userId = ? WHERE id = ?", tableName);
            connexion();
            initPreparedStatement(query);
            ps.setString(1, client.getSurnom());
            ps.setString(2, client.getTelephone());
            ps.setString(3, client.getAdresse());
            ps.setInt(4, client.getUser().getId());
            ps.setInt(5, client.getId());
            executeUpdate();

            // Print SQL statement for debugging
            // System.out.println("Executing SQL: " + statement.toString());
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
    }

    public Client converToObjet(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setSurnom(rs.getString("surnom"));
        client.setTelephone(rs.getString("telephone"));
        client.setAdresse(rs.getString("adresse"));
        int userId = rs.getInt("userId");
        // MODE EAGER
        User user = this.userRepository.selectById(userId);
        client.setUser(user);
        // MODE LAZY
        // User user = new User();
        // user.setId(userId);
        // client.setUser(user);
        return client;
    }

}
