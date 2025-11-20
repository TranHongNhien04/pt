package dao;

import entity.StudentExam;

import jakarta.persistence.EntityManager;

public class StudentExam_DAO extends Generic_DAO<StudentExam, Long> {
    public StudentExam_DAO(Class<StudentExam> clazz) {
        super(clazz);
    }

    public StudentExam_DAO(EntityManager em, Class<StudentExam> clazz) {
        super(em, clazz);
    }
}
