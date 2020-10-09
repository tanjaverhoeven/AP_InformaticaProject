package edu.ap.projectteambisfits.external;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ap.projectteambisfits.email.EmailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ExternalController {

    @Autowired
    private ExternalService service;

    @Autowired
    private EmailService emailService;

    @GetMapping(path = "/external", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<External> GetAll() {
        return service.findAll();
    }

    @GetMapping(path = "/external/{id}")
    public External GetById(@PathVariable("id") String id) {
        External u = service.findById(id);
        return u;
    }

    @PostMapping(path = "/external")
    public ResponseEntity<?> SaveExternal(@RequestBody External external) {
        service.saveExternal(external);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/external/{id}")
    public void DeleteExternal(@PathVariable("id") String id) {
        External external = service.findById(id);
        service.deleteExternal(external);
    }

    @PostMapping(path = "/external/email")
    public void emailExternal(@RequestBody ExternalEmailModel externalmail) throws MessagingException {
        emailService.sendEmailForExternalFirm(externalmail.getBody(), externalmail.getRecipient(),
                externalmail.getSubject(), externalmail.getCarboncopies());
    }
}