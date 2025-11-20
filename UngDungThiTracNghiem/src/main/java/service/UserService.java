package service;

import entity.User;

import java.rmi.RemoteException;

public interface UserService extends GenericService<User, Long> {
    User findByUsername(String s) throws RemoteException;
}
