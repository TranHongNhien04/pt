package dao;

import entity.User;
import jakarta.persistence.EntityManager;

public class User_DAO extends Generic_DAO<User, Long> {
    public User_DAO(Class<User> clazz) {
        super(clazz);
    }

    public User_DAO(EntityManager em, Class<User> clazz) {
        super(em, clazz);
    }

    public User findByUsername(String s) {
        return em.createQuery("from User where username like :s", User.class)
                .setParameter("s", "%"+s+"%")
                .getSingleResult();
    }
}

