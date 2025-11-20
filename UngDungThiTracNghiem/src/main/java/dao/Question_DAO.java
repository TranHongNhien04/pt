package dao;

import entity.Question;

import jakarta.persistence.EntityManager;

public class Question_DAO extends Generic_DAO<Question, Long> {
    public Question_DAO(Class<Question> clazz) {
        super(clazz);
    }

    public Question_DAO(EntityManager em, Class<Question> clazz) {
        super(em, clazz);
    }
}
