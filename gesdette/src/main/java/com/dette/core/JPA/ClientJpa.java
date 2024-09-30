package com.dette.core.JPA;

import com.dette.core.database.implement.RepositoryJpaImpl;
import com.dette.entities.Client;
import com.dette.repository.implement.ClientRepository;

public class ClientJpa extends RepositoryJpaImpl<Client> implements ClientRepository {

    public ClientJpa() {
        super(Client.class);
    }

    @Override
    public Client selectBy(String name) {
        try {
            Client result = em.createQuery("SELECT c FROM Client c WHERE c.telephone = :telephone", Client.class)
                    .setParameter("telephone", name)
                    .getSingleResult();
            if (result == null) {
                System.out.println("Client non existant");
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void update(Client client) {
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
    }

}
