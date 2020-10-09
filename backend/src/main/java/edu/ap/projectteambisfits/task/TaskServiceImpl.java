package edu.ap.projectteambisfits.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(String id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

}