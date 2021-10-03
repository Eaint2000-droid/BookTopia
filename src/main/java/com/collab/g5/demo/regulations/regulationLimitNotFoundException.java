package com.collab.g5.demo.regulations;

public class regulationLimitNotFoundException extends RuntimeException {
    public regulationLimitNotFoundException(){
        super("no regulation limit found");
    }
}
