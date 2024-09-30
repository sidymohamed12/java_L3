package com.dette.core.JPA;

import org.mindrot.jbcrypt.BCrypt;

import com.dette.core.database.implement.RepositoryJpaImpl;
import com.dette.entities.User;
import com.dette.repository.implement.UserRepository;

public class UserJpa extends RepositoryJpaImpl<User> implements UserRepository {

    public UserJpa() {
        super(User.class);
    }

    @Override
    public User selectBy(String name) {
        try {
            User result = em.createQuery("SELECT c FROM User c WHERE c.login = :login", User.class)
                    .setParameter("login", name)
                    .getSingleResult();
            if (result == null) {
                System.out.println("User non existant");
                return null;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User selectById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
