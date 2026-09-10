package com.malavika.taskmanager;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(){
        super("Invalid username or password");
    }

}
