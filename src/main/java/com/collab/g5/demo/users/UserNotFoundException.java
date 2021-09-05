package com.collab.g5.demo.users;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(String email){
        super("Could not find User email: "+ email);
    }
    UserNotFoundException(){
        super("No users found");
    }
}
