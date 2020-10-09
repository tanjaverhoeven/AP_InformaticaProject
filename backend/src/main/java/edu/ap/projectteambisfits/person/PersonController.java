package edu.ap.projectteambisfits.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.projectteambisfits.defect.Defect;
import edu.ap.projectteambisfits.defect.DefectService;
import edu.ap.projectteambisfits.enums.Status;
import edu.ap.projectteambisfits.external.External;
import edu.ap.projectteambisfits.external.ExternalService;
import edu.ap.projectteambisfits.task.Task;
import edu.ap.projectteambisfits.task.TaskService;
import edu.ap.projectteambisfits.user.User;
import edu.ap.projectteambisfits.user.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExternalService externalService;

    @Autowired
    private DefectService defectService;

    @Autowired
    private TaskService taskService;

    @GetMapping(path = "/user/defects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> assignedDefects(@PathVariable String id) {
        User u = userService.findById(id);
        return u.getAssignedDefects();
    }

    @GetMapping(path = "/user/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> assignedTasks(@PathVariable String id) {
        User u = userService.findById(id);
        return u.getAssignedTasks();
    }

    @PostMapping(path = "/user/assigndefect/{userid}/{defectid}")
    public Person postAssignDefect(@PathVariable String userid, @PathVariable String defectid) {

        Defect d = defectService.getDefect(defectid);
        Person u = userService.findById(userid);
        System.out.println(userid);

        if (d.getStatus().equals(Status.PROCESS_BY_AP)) {
            u.addAssignedDefects(defectid);
            userService.saveUser((User) u);

            d.setFixerid(userid);
            d.setStatus(Status.EXECUTING_BY_AP);
            defectService.saveDefect(d);
        } else {
            String fixerid = d.getFixerid();
            User previousFixer = userService.findById(fixerid);
            previousFixer.deleteAssignedDefect(defectid);
            userService.saveUser(previousFixer);

            u.addAssignedDefects(defectid);
            userService.saveUser((User) u);

            d.setFixerid(userid);
            d.setStatus(Status.EXECUTING_BY_AP);
            defectService.saveDefect(d);

        }
        return u;
    }

    @PostMapping(path = "/external/{userid}/assign/defect/{defectid}")
    public Person postAssignDefectExternal(@PathVariable String userid, @PathVariable String defectid) {
        Defect d = defectService.getDefect(defectid);
        Person u = externalService.findById(userid);

        u.addAssignedDefects(defectid);
        externalService.saveExternal((External) u);

        d.setFixerid(userid);
        d.setStatus(Status.EXECUTING_BY_EXTERNAL);
        defectService.saveDefect(d);

        return u;
    }

    @PostMapping(path = "/user/assigntask/{userid}/{taskid}")
    public Person postAssignTask(@PathVariable String userid, @PathVariable String taskid) {

        Task t = taskService.getTask(taskid);
        Person u = userService.findById(userid);

        if (t.getStatus().equals(Status.PROCESS_BY_AP)) {
            u.addAssignedTask(taskid);
            userService.saveUser((User) u);

            t.setFixerid(userid);
            t.setStatus(Status.EXECUTING_BY_AP);
            taskService.saveTask(t);
        } else {
            String fixerid = t.getFixerid();
            Person previousFixer = userService.findById(fixerid);
            previousFixer.deleteAssignedTask(taskid);
            userService.saveUser((User) previousFixer);

            u.addAssignedTask(taskid);
            userService.saveUser((User) u);

            t.setFixerid(userid);
            t.setStatus(Status.EXECUTING_BY_AP);
            taskService.saveTask(t);
        }
        return u;
    }

    @PostMapping(path = "/external/{userid}/assign/task/{taskid}")
    public Person postAssignTaskExternal(@PathVariable String userid, @PathVariable String taskid) {
        Task t = taskService.getTask(taskid);
        Person u = externalService.findById(userid);

        u.addAssignedTask(taskid);
        externalService.saveExternal((External) u);

        t.setFixerid(userid);
        t.setStatus(Status.EXECUTING_BY_EXTERNAL);
        taskService.saveTask(t);

        return u;
    }
}