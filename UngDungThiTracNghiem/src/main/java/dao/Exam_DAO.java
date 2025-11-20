package dao;

import entity.Exam;

import jakarta.persistence.EntityManager;

public class Exam_DAO extends Generic_DAO<Exam, Long> {
    public Exam_DAO(Class<Exam> clazz) {
        super(clazz);
    }

    public Exam_DAO(EntityManager em, Class<Exam> clazz) {
        super(em, clazz);
    }
}
