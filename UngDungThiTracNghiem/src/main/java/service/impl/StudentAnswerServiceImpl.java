package service.impl;

import dao.StudentAnswer_DAO;
import entity.StudentAnswer;
import service.StudentAnswerService;

import java.rmi.RemoteException;

public class StudentAnswerServiceImpl extends GenericServiceImpl<StudentAnswer,Long> implements StudentAnswerService {
    private StudentAnswer_DAO studentAnswer_dao;

    public StudentAnswerServiceImpl(StudentAnswer_DAO studentAnswer_dao) throws RemoteException {
        super(studentAnswer_dao);
        this.studentAnswer_dao = studentAnswer_dao;
    }
}
