package iuh.fit.se.controller;

import entity.Exam;
import entity.Question;
import entity.User;
import service.ExamService;
import service.QuestionService;
import service.UserService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

public class ClientController {
    private final UserService userService;
    private final ExamService examService;
    private final QuestionService questionService;

    public ClientController(String host, int port) throws Exception {
        String rmiUrlPrefix = "rmi://" + host + ":" + port + "/";
        userService = (UserService) Naming.lookup(rmiUrlPrefix + "userService");
        examService = (ExamService) Naming.lookup(rmiUrlPrefix + "examService");
        questionService = (QuestionService) Naming.lookup(rmiUrlPrefix + "questionService");
    }

    public User login(String username, String password) throws RemoteException {
        return userService.login(username, password);
    }

    // Student methods
    public List<Exam> getExamsForStudent() throws RemoteException {
        return examService.getExamsForStudent();
    }

    public Exam startExam(String examName) throws RemoteException {
        return examService.startExam(examName);
    }

    // Teacher - Question management
    public List<Question> getAllQuestions() throws RemoteException {
        return questionService.getAll();
    }

    public void addQuestion(Question question) throws RemoteException {
        questionService.addQuestion(question);
    }

    public void updateQuestion(Question question) throws RemoteException {
        questionService.updateQuestion(question);
    }

    public void deleteQuestion(int id) throws RemoteException {
        questionService.deleteQuestion(id);
    }

    // Teacher - Exam management
    public List<Exam> getAllExams() throws RemoteException {
        return examService.getAll();
    }

    public void addExam(Exam exam) throws RemoteException {
        examService.addExam(exam);
    }

    public void updateExam(Exam exam) throws RemoteException {
        examService.updateExam(exam);
    }

    public void deleteExam(int id) throws RemoteException {
        examService.deleteExam(id);
    }

    public void addQuestionToExam(int examId, int questionId) throws RemoteException {
        examService.addQuestionToExam(examId, questionId);
    }
}
