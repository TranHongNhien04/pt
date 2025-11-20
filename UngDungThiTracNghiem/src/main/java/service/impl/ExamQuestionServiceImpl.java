package service.impl;

import dao.ExamQuestion_DAO;
import entity.ExamQuestion;
import service.ExamQuestionService;

import java.rmi.RemoteException;

public class ExamQuestionServiceImpl extends GenericServiceImpl<ExamQuestion,Long> implements ExamQuestionService {
    private ExamQuestion_DAO examQuestion_dao;

    public ExamQuestionServiceImpl(ExamQuestion_DAO examQuestion_dao) throws RemoteException {
        super(examQuestion_dao);
        this.examQuestion_dao = examQuestion_dao;
    }
}
