import jakarta.persistence.EntityManager;
import util.EntityManagerFactoryUtil;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Runner {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(8888);

        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();

//        StudentService studentService = new StudentServiceImpl(entityManager); // Remote Object
//
//        registry.rebind("TNService", studentService);

        System.out.println("RMI Server ready");

    }
}