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
}
