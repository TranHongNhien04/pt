package service.impl;

import dao.StudentExam_DAO;
import entity.StudentExam;
import service.StudentExamService;

import java.rmi.RemoteException;

public class StudentExamServiceImpl extends GenericServiceImpl<StudentExam,Long> implements StudentExamService {
    private StudentExam_DAO studentExamService_dao;

    public StudentExamServiceImpl(StudentExam_DAO studentExamService_dao) throws RemoteException {
        super(studentExamService_dao);
        this.studentExamService_dao = studentExamService_dao;
    }
}
