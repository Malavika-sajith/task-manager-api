package com.malavika.taskmanager;

public class TaskNotFoundException extends RuntimeException{
    TaskNotFoundException(Long id){
        super("Task not found with id: " +id);
    }
}
