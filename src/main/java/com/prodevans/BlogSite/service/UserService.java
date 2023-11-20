package com.prodevans.BlogSite.service;

import com.prodevans.BlogSite.model.Users;

import java.util.List;

public interface UserService {
    public Users createUser(Users users);
    public Users getUser(String name);
    public Users updateUsers(Users users);
    public Users deleteUsers(Users users);
    public List<Users> getAllUsers();
}
