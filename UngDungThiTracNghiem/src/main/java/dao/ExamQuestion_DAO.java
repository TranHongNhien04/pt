package dao;

import entity.ExamQuestion;

import jakarta.persistence.EntityManager;

public class ExamQuestion_DAO extends Generic_DAO<ExamQuestion, Long> {
    public ExamQuestion_DAO(Class<ExamQuestion> clazz) {
        super(clazz);
    }

    public ExamQuestion_DAO(EntityManager em, Class<ExamQuestion> clazz) {
        super(em, clazz);
    }
}
