package com.dette.core.database.implement;

import java.util.List;

import com.dette.core.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RepositoryJpaImpl<T> implements Repository<T> {

    protected EntityManager em;
    protected EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MYSQLDETTE");
    private Class<T> type;

    public RepositoryJpaImpl(Class<T> type) {
        this.type = type;
        if (em == null) {
            em = emFactory.createEntityManager();
        }
    }

    @Override
    public void insert(T value) {
        em.getTransaction().begin();
        em.persist(value);
        em.getTransaction().commit();
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

}
