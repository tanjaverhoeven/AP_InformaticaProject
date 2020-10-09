package edu.ap.projectteambisfits.task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task getTask(String id);

    void deleteTask(String id);

    Task saveTask(Task task);

}