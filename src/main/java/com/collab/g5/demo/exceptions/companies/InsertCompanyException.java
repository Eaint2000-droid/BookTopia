package com.collab.g5.demo.exceptions.companies;

public class InsertCompanyException extends RuntimeException{
    public InsertCompanyException(String name) { super("This "+ name+ " already taken"); }
}
