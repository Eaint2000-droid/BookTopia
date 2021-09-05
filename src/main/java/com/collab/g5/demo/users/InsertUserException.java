package com.collab.g5.demo.users;

public class InsertUserException extends RuntimeException{
    InsertUserException(String email){
        super("email "+ email+ " already taken");
    }

}
