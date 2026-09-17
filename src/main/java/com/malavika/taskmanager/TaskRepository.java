package com.malavika.taskmanager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.beans.JavaBean;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
