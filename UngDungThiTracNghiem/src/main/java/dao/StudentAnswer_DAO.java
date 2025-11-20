package dao;

import entity.StudentAnswer;

import jakarta.persistence.EntityManager;

public class StudentAnswer_DAO extends Generic_DAO<StudentAnswer, Long> {
    public StudentAnswer_DAO(Class<StudentAnswer> clazz) {
        super(clazz);
    }

    public StudentAnswer_DAO(EntityManager em, Class<StudentAnswer> clazz) {
        super(em, clazz);
    }
}
