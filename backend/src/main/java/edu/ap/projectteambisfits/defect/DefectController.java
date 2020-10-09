package edu.ap.projectteambisfits.defect;

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

import edu.ap.projectteambisfits.user.User;
import edu.ap.projectteambisfits.user.UserServiceImpl;
import edu.ap.projectteambisfits.email.EmailService;
import edu.ap.projectteambisfits.enums.Priority;
import edu.ap.projectteambisfits.enums.Status;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DefectController {

    @Autowired
    private DefectService defectService;

    private UserServiceImpl userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    public DefectController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/defects", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Defect> getAllDefects() {
        return defectService.findAll();
    }

    @GetMapping(path = "/defectsstatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Defect> getDefectByStatus(@PathVariable String status) {
        List<Defect> listStatus = new ArrayList<Defect>();
        List<Defect> defects = defectService.findAll();
        for (Defect defect : defects) {
            if (defect.getStatus().equals(Status.PROCESS_BY_AP)) {
                listStatus.add(defect);
            }
        }
        return listStatus;
    }

    @GetMapping(path = "/defectsnotopen")
    public List<Defect> getDefectsNotOpen() {
        List<Defect> listStatus = new ArrayList<Defect>();
        List<Defect> defects = defectService.findAll();
        for (Defect defect : defects) {
            if (defect.getStatus().equals(Status.EXECUTING_BY_AP) || defect.getStatus().equals(Status.EXECUTING_BY_EXTERNAL)) {
                listStatus.add(defect);
            }
        }
        return listStatus;
    }

    @GetMapping(path = "/defects/archived")
    public List<Defect> getDefectsArchived() {
        List<Defect> listStatus = new ArrayList<Defect>();
        List<Defect> defects = defectService.findAll();
        for (Defect defect : defects) {
            if (defect.getStatus().equals(Status.COMPLETE) || defect.getStatus().equals(Status.CANCELED)) {
                listStatus.add(defect);
            }
        }
        return listStatus;
    }

    @GetMapping(path = "/defects/processed")
    public List<Defect> getDefectsprocessed() {
        List<Defect> listStatus = new ArrayList<Defect>();
        List<Defect> defects = defectService.findAll();
        for (Defect defect : defects) {
            if (defect.getStatus().equals(Status.PASSED_TO_PROVINCE)
                    || defect.getStatus().equals(Status.PROCESS_BY_AP)) {
                listStatus.add(defect);
            }
        }
        return listStatus;
    }
    // #endregion

    @GetMapping(path = "/defects/{id}")
    public Defect getDefect(@PathVariable String id) {
        Defect defect = defectService.getDefect(id);
        return defect;
    }

    @PostMapping(path = "/defect")
    public ResponseEntity<?> newDefect(@RequestBody Defect defect) throws MessagingException {
        defectService.saveDefect(defect);
        emailService.sendDefectCreatedEmail(defect.getId(), defect.getName(), defect.getCategory().getId());
        return new ResponseEntity<>(defect.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/defect/{id}")
    public ResponseEntity<?> deleteDefectById(@PathVariable String id) {
        // Delete defect uit user
        Defect d = defectService.getDefect(id);
        if (!d.getFixerid().equals("")) {
            String userid = d.getFixerid();
            User u = userService.findById(userid);
            u.deleteAssignedDefect(id);
            userService.saveUser(u);
        }

        // Delete defect
        defectService.deleteDefect(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/defect/{id}")
    public ResponseEntity<?> updateDefect(@RequestBody Defect defect, @PathVariable String id) {
        Defect d = getDefect(id);
        d.setId(id);
        d.setName(defect.getName());
        d.setDescription(defect.getDescription());
        d.setCategory(defect.getCategory());
        d.setCampuslocation(defect.getCampuslocation());
        d.setLocationroom(defect.getLocationroom());
        d.setNearby(defect.getNearby());
        d.setPhotoid(defect.getPhotoid());
        defectService.saveDefect(d);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // #region upvotes
    @PutMapping(path = "/defect/{id}/upvote/{isUpvote}")
    public ResponseEntity<?> updateDefectUpvote(@PathVariable String id, @PathVariable boolean isUpvote) {
        Defect tempDef = getDefect(id);
        if (isUpvote) {
            tempDef.setUpvotes(tempDef.getUpvotes() + 1);
        } else {
            tempDef.setUpvotes(tempDef.getUpvotes() - 1);
        }
        if (tempDef.getUpvotes() > Priority.MEDIUM.getThreshold()) {
            tempDef.setPriority(Priority.HIGH);
        } else if (tempDef.getUpvotes() > Priority.LOW.getThreshold()) {
            tempDef.setPriority(Priority.MEDIUM);
        } else {
            tempDef.setPriority(Priority.LOW);
        }
        Defect d = defectService.saveDefect(tempDef);
        return new ResponseEntity<>(d.getUpvotes(), HttpStatus.OK);
    }

    @GetMapping(path = "/defect/status/{id}")
    public String getSatusDefect(@PathVariable String id) {
        Defect d = defectService.getDefect(id);

        return d.getStatus().getVal();
    }

    @PutMapping(path = "/defect/status/{id}")
    public ResponseEntity<?> updateSatusDefect(@PathVariable String id, @RequestBody Defect defect)
            throws MessagingException {
        Defect d = defectService.getDefect(id);
        d.setStatus(defect.getStatus());
        d.setStatusChanged();
        defectService.saveDefect(d);
        if (defect.getStatus().equals(Status.COMPLETE)) {
            this.saveNotifications(d);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/defect/{id}/subscribe/{userId}")
    public ResponseEntity<?> subscribeToDefect(@PathVariable String userId, @PathVariable String id) {
        Defect d = defectService.getDefect(id);
        d.subscribe(userId);
        defectService.saveDefect(d);
        return new ResponseEntity<>("User is subscribed", HttpStatus.OK);
    }

    @PutMapping(path = "/defect/{id}/unsubscribe/{userId}")
    public ResponseEntity<?> unsubscribeToDefect(@PathVariable String userId, @PathVariable String id) {
        Defect d = defectService.getDefect(id);
        d.unsubscribe(userId);
        defectService.saveDefect(d);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    private void saveNotifications(Defect defect) {
        defect.getObservers().stream().forEach((observer) -> {
            User u = userService.findById(observer);
            u.receiveUpdate(defect.getId());
            userService.saveUser(u);
        });
    }
    // #endregion
}