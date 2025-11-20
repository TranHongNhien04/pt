package service.impl;

import dao.Question_DAO;
import entity.Question;
import service.QuestionService;


import java.rmi.RemoteException;

public class QuestionServiceImpl extends GenericServiceImpl<Question,Long> implements QuestionService {
    private Question_DAO question_dao;

    public QuestionServiceImpl(Question_DAO question_dao) throws RemoteException {
        super(question_dao);
        this.question_dao = question_dao;
    }
}
