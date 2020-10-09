package edu.ap.projectteambisfits.task;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.projectteambisfits.email.EmailService;
import edu.ap.projectteambisfits.enums.Status;
import edu.ap.projectteambisfits.user.User;
import edu.ap.projectteambisfits.user.UserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;

    private UserServiceImpl userService;

    @Autowired
    public TaskController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping(path = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getTask(@PathVariable String id) {
        Task task = taskService.getTask(id);
        return task;
    }

    @GetMapping(path = "/tasks/processed")
    public List<Task> getTasksProcessed() {
        List<Task> listStatus = new ArrayList<Task>();
        List<Task> tasks = taskService.findAll();
        for (Task task : tasks) {
            if (task.getStatus().equals(Status.PASSED_TO_PROVINCE) || task.getStatus().equals(Status.PROCESS_BY_AP)) {
                listStatus.add(task);
            }
        }
        return listStatus;
    }

    @PostMapping(path = "/task")
    public ResponseEntity<?> newTask(@RequestBody Task task) throws MessagingException {
        taskService.saveTask(task);
        emailService.sendTaskCreatedEmail(task.getId(), task.getName(), task.getCategory().getId());
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/task/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable String id) {
        Task t = taskService.getTask(id);
        if (!t.getFixerid().equals("")) {
            String userid = t.getFixerid();
            User u = userService.findById(userid);
            u.deleteAssignedTask(id);
            userService.saveUser(u);
        }

        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable String id) {
        Task t = getTask(id);
        t.setId(id);
        t.setName(task.getName());
        t.setDescription(task.getDescription());
        t.setCategory(task.getCategory());
        t.setCampuslocation(task.getCampuslocation());
        t.setLocationroom(task.getLocationroom());
        t.setNearby(task.getNearby());
        taskService.saveTask(t);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/task/{id}/status")
    public ResponseEntity<?> updateSatusTask(@PathVariable String id, @RequestBody Task task) {
        Task t = taskService.getTask(id);
        t.setStatus(task.getStatus());
        t.setStatusChanged();
        taskService.saveTask(t);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/task/{id}/status")
    public String getStatusTask(@PathVariable String id) {
        Task t = taskService.getTask(id);

        return t.getStatus().getVal();
    }

    @GetMapping(path = "/tasks/archived")
    public List<Task> getTasksArchived() {
        List<Task> listStatus = new ArrayList<Task>();
        List<Task> tasks = taskService.findAll();

        for (Task task : tasks) {
            if (task.getStatus().equals(Status.COMPLETE) || task.getStatus().equals(Status.CANCELED)) {
                listStatus.add(task);
            }
        }
        return listStatus;
    }
}