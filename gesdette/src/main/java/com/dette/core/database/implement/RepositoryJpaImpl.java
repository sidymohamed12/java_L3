package com.dette.core.database.implement;

import java.io.InputStream;
import java.util.*;

import com.dette.core.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.yaml.snakeyaml.Yaml;

public class RepositoryJpaImpl<T> implements Repository<T> {

    protected EntityManager em;
    protected EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("POSTGRESDETTE");
    private Class<T> type;

    public RepositoryJpaImpl(Class<T> type) {
        this.type = type;

        // Charger la configuration depuis config.yaml
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.yaml")) {
            Map<String, Object> config = yaml.load(inputStream);
            String persistenceUnit = (String) ((Map<String, Object>) config.get("persistence")).get("unit");

            // Créer l'EntityManagerFactory en fonction de l'unité de persistance
            emFactory = Persistence.createEntityManagerFactory(persistenceUnit);

            if (em == null) {
                em = emFactory.createEntityManager();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(T value) {
        try {
            em.getTransaction().begin();
            em.persist(value);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<T> selectAll() {
        String sql = String.format("SELECT t FROM %s t", type.getSimpleName());
        return em.createQuery(sql, type).getResultList();
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    // :tel
    // setParameter("tel", telephone);

    // faire remove et selectby

}
