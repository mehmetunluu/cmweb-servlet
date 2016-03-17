package com.backend.exercise.auth;

import com.backend.exercise.hibernate.entity.impl.User;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by M.UNLU on 02.03.2016.
 */
public class InMemoryUser {

    private final ConcurrentHashMap<Integer, User> authUsers = new ConcurrentHashMap<Integer,User>();

    private static final Object lock = new Object();

    private static  InMemoryUser inMemoryUser=null;
    private InMemoryUser(){

    }

    public static  InMemoryUser getInMemoryUser(){
        if(inMemoryUser==null){
            synchronized (lock){
                inMemoryUser= new InMemoryUser();
            }
        }
        return inMemoryUser;
    }

    public boolean isAuth(Integer userId,User user){

        User authUser =authUsers.get(userId);
        if(authUser==null){
            return false;
        }
        if(authUser.getName().equals(user.getName())){
            return  true;
        }
        return  false;
    }

    public User getUserByName(String  username){

        ConcurrentHashMap.KeySetView<Integer, User> authUser =authUsers.keySet();
        while(authUser.getMap().keys().hasMoreElements()){
            User user =authUsers.get(authUser.getMap().keys().nextElement());
            if(user.getName().equals(username)){
                return  user;
            }
        }
        return  null;
    }

    public void addAuthUser(User user){
        this.authUsers.put(user.getId(),user);
    }

    public void removeAuthUser(User user){
        this.authUsers.remove(user.getId(),user);
    }


}
