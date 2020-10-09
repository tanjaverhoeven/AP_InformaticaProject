package edu.ap.projectteambisfits.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getAllRoles() {
        roleService.findAll();
        return roleService.findAll();
    }

    @PostMapping(path = "/role")
    public ResponseEntity<?> newRole(@RequestBody Role role) {
        roleService.saveRole(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);

    }
}