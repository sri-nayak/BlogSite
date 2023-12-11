package com.prodevans.BlogSite.controller;


import com.prodevans.BlogSite.model.Users;
import com.prodevans.BlogSite.service.UserService;
import com.prodevans.BlogSite.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")

public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }


    /**
     *
     * @param user
     * @return ResponseEntity
     */
    @PostMapping("/create-user")
    public ResponseEntity saveUser(@RequestBody Users user){
       if (userService.createUser(user)!=null)
           return new ResponseEntity<>(HttpStatus.CREATED);
       else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param user
     * @return ResponseEntity
     */
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody Users user){
       return new ResponseEntity<>(userService.updateUsers(user),HttpStatus.ACCEPTED);
    }

    /**
     *
     * @param user
     * @return ResponseEntity
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody Users user){
        if (userService.deleteUsers(user)!=null)
            return new ResponseEntity<>("Resource Deleted",HttpStatus.ACCEPTED);
        else return new ResponseEntity("Resource coudldn't find",HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @return ResponseEntity
     */
    @GetMapping("/getAll")
    public ResponseEntity getAllUser()
    {
        List<Users> usersList=userService.getAllUsers();
        if (usersList.isEmpty()){
            return new ResponseEntity("Data Not Available",HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usersList);
    }
}
