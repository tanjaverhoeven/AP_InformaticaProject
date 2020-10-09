package edu.ap.projectteambisfits.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "/departments")
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @GetMapping(path = "/department/{depid}")
    public Department getDepartmentById(@PathVariable String depid) {
        return departmentService.findById(depid);
    }

    @PostMapping(path = "/department")
    public ResponseEntity<?> newDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
        return new ResponseEntity<>("Department added succesfully", HttpStatus.CREATED);
    }

    @PostMapping(path = "/department/contact/{departmentID}")
    public ResponseEntity<?> newContact(@PathVariable("departmentID") String departmentID,
            @RequestBody Contact contact) {
        Department dep = departmentService.findById(departmentID);
        dep.addContactToList(contact);
        departmentService.saveDepartment(dep);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/department/{depid}/contact/{contname}")
    public ResponseEntity<?> deleteContactById(@PathVariable String depid, @PathVariable String contname) {
        Department founddep = departmentService.findById(depid);
        founddep.setId(depid);
        founddep.removeContactFromList(contname);
        departmentService.saveDepartment(founddep);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/department/{departmentid}/{contactid}")
    public ResponseEntity<?> updateContact(@RequestBody Contact newContact, @PathVariable String departmentid,
            @PathVariable String contactid) {
        Department d = departmentService.findById(departmentid);
        if (d != null) {
            d.updateContactList(contactid, newContact);
            d.setId(departmentid);
            departmentService.saveDepartment(d);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}