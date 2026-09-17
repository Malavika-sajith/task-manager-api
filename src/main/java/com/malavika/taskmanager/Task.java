package com.malavika.taskmanager;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title cannot be empty")


    private String title;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId(){
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getTitle(){

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isCompleted(){

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
