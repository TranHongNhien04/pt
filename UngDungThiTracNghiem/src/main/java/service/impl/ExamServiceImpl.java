package service.impl;

import dao.Exam_DAO;
import entity.Exam;
import service.ExamService;

import java.rmi.RemoteException;

public class ExamServiceImpl extends GenericServiceImpl<Exam,Long> implements ExamService {
    private Exam_DAO exam_dao;

    public ExamServiceImpl(Exam_DAO exam_dao) throws RemoteException {
        super(exam_dao);
        this.exam_dao = exam_dao;
    }
}
