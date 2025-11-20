package rmi;

import dao.*;
import entity.*;
import service.*;
import service.impl.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception{

        Context context = new InitialContext();
        LocateRegistry.createRegistry(1262);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppTN");
        EntityManager em = emf.createEntityManager();

        //
        Exam_DAO examDao = new Exam_DAO(Exam.class);
        ExamQuestion_DAO examQuestionDao = new ExamQuestion_DAO(ExamQuestion.class);
        Question_DAO questionDao = new Question_DAO(Question.class);
        StudentAnswer_DAO studentAnswerDao = new StudentAnswer_DAO(StudentAnswer.class);
        StudentExam_DAO studentExamDao = new StudentExam_DAO(StudentExam.class);
        User_DAO userDAO = new User_DAO(User.class);

        //

        ExamQuestionService examQuestionService = new ExamQuestionServiceImpl(examQuestionDao);
        ExamService examService = new ExamServiceImpl(examDao);
        QuestionService questionService = new QuestionServiceImpl(questionDao);
        StudentAnswerService studentAnswerService = new StudentAnswerServiceImpl(studentAnswerDao);
        StudentExamService studentExamService = new StudentExamServiceImpl(studentExamDao);
        UserService userService = new UserServiceImpl(userDAO);


        // Bind service
        context.bind("rmi://localhost:1262/examService", examService);
        context.bind("rmi://localhost:1262/questionService", questionService);
        context.bind("rmi://localhost:1262/examQuestionService", examQuestionService);
        context.bind("rmi://localhost:1262/studentExamService", studentExamService);
        context.bind("rmi://localhost:1262/studentAnswerService", studentAnswerService);
        context.bind("rmi://localhost:1262/userService", userService);


        System.out.println("RMI Server is running...");
    }
}