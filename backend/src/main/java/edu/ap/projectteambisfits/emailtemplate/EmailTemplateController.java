package edu.ap.projectteambisfits.emailtemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmailTemplateController {
  
  @Autowired
  private EmailTemplateService emailTemplateService;

  @GetMapping(path = "/emailtemplates", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<EmailTemplate> getAllEmailTemplates() {
    return emailTemplateService.findAll();
  }

  @GetMapping(path="/emailtemplate/{id}")
  public EmailTemplate getEmailTemplate(@PathVariable String id) {
    EmailTemplate emailTemplate = emailTemplateService.getEmailTemplate(id);
    return emailTemplate;
  }

  @PostMapping(path="/emailtemplate")
  public ResponseEntity<?> newEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
    emailTemplateService.saveEmailTemplate(emailTemplate);
    return new ResponseEntity<>(emailTemplate, HttpStatus.CREATED);
  }

  @PutMapping(path="/emailtemplate/{id}")
  public EmailTemplate updateEmailTemplate(@RequestBody EmailTemplate emailTemplate, @PathVariable String id) {
    EmailTemplate eT = getEmailTemplate(id);
    eT.setText(emailTemplate.getText());
    emailTemplateService.saveEmailTemplate(eT);
    return eT;
  }

}