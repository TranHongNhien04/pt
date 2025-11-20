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
        User_DAO userDAO = new User_DAO(User.class);

        //
        UserService userService = new UserServiceImpl(userDAO);

        // Bind service
        context.bind("rmi://localhost:1262/userService", userService);

        System.out.println("RMI Server is running...");
    }
}