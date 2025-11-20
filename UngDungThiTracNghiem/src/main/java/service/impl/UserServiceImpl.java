package service.impl;

import dao.User_DAO;
import entity.User;
import service.UserService;

import java.rmi.RemoteException;

public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {
    private User_DAO userDao;

    public UserServiceImpl(User_DAO userDao) throws RemoteException {
        super(userDao);
        this.userDao = userDao;
    }
}
