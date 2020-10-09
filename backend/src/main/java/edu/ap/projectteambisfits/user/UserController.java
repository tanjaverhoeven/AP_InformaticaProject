package edu.ap.projectteambisfits.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") String id) {
        User user = userService.findById(id);
        return user;
    }

    @PostMapping(path = "/user")
    public User newUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping(path = "/users/{id}/upvote")
    public User addUpvoteToUser(@PathVariable("id") String id, String defectId) {
        User u = this.getUser(id);
        u.addUpvoteToUpvotedDefects(defectId);
        userService.saveUser(u);
        return u;
    }

    @PutMapping(path = "/user")
    public ResponseEntity<?> editRole(@RequestBody User editedUser) {
        User user = userService.findById(editedUser.getId());
        user.setRole(editedUser.getRole());
        userService.saveUser(user);
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }

    @GetMapping(path = "/user/role/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getAdminCount() {
        return userService.findByRole("ADMIN").size();
    }

    @GetMapping(path = "/users/{id}/upvoted", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllUpvotedDefectsOfUser(@PathVariable("id") String id) {
        User user = userService.findById(id);
        return user.getUpvoted();
    }

    @DeleteMapping(path = "/users/{id}/upvoted/{defId}")
    public User deleteUpvotedDefectForUser(@PathVariable("id") String id, @PathVariable("defId") String defId) {
        User user = getUser(id);
        user.getUpvoted().remove(defId);
        userService.saveUser(user);
        return user;
    }

    @PutMapping(path = "/users/{id}/upvoted/{defId}")
    public User addUpvotedDefectForUser(@PathVariable("id") String id, @PathVariable("defId") String defId) {
        User user = getUser(id);
        user.getUpvoted().add(defId);
        userService.saveUser(user);
        return user;
    }

    @GetMapping(path = "/user/{id}/notifications")
    public List<Notification> getNotifications(@PathVariable("id") String id) {
        User u = userService.findById(id);

        return u.getNotifications();
    }

    @DeleteMapping(path = "/user/{id}/notifications")
    public User deleteNotifications(@PathVariable("id") String id) {
        User u = userService.findById(id);
        u.getNotifications().clear();
        userService.saveUser(u);
        return u;
    }

    @DeleteMapping(path = "/user/{id}/notification/{index}")
    public User deleteNotificationForUser(@PathVariable("id") String id, @PathVariable("index") int notificatonIndex) {
        User u = userService.findById(id);
        u.getNotifications().remove(notificatonIndex);
        userService.saveUser(u);
        return u;
    }

    @PutMapping(path = "/user/{id}/notification")
    public User updateNotificationToRead(@PathVariable("id") String id) {
        User u = userService.findById(id);
        u.getNotifications().stream().forEach((notification) -> {
            notification.setIsRead(true);
        });
        userService.saveUser(u);
        return u;
    }

}