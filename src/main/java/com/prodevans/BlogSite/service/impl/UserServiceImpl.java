package com.prodevans.BlogSite.service.impl;

import com.prodevans.BlogSite.Repository.UserRepository;
import com.prodevans.BlogSite.model.Users;
import com.prodevans.BlogSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepositoy;
    @Autowired
    public UserServiceImpl(UserRepository userRepositoy){
        this.userRepositoy=userRepositoy;
    }

    /**
     *
     * @param users
     * @return
     */
    @Override
    public Users createUser(Users users) {
        List<Users> users1=userRepositoy.findAll().stream()
                .filter(users2 -> users2.getUserName().equalsIgnoreCase(users.getUserName()) ||users2.getEmail().equalsIgnoreCase(users.getEmail())).toList();
        try {
            if (users1.isEmpty())
            return userRepositoy.save(users);
            else{
                return userRepositoy.findByEmail(users.getEmail());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public Users getUser(String name) {
        try {
            Users users=userRepositoy.findByEmail(name);
            return users;
        }catch (Exception e){
            System.out.println("Exceptin e");
            return null;
        }
    }

    /**
     *
     * @param users
     * @return
     */
    @Override
    public Users updateUsers(Users users) {
        try {
            Users users1=userRepositoy.save(users);
            return users1;
        }catch (Exception e){
            System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        }
        return null;
    }

    /**
     *
     * @param users
     * @return
     */
    @Override
    public Users deleteUsers(Users users) {
        try {
            userRepositoy.delete(users);
             return users;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<Users> getAllUsers() {
        return userRepositoy.findAll();
    }
}
