package com.malavika.taskmanager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TaskTest {

    @Test
    void  testTaskGettersAndSetters(){
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Learn Testing");
        task.setCompleted(true);

        assertEquals(1L, task.getId());
        assertEquals("Learn Testing", task.getTitle());
        assertTrue(task.isCompleted());
    }

}
