package edu.ap.projectteambisfits.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import edu.ap.projectteambisfits.category.Category;
import edu.ap.projectteambisfits.category.PrimaryCategory;
import edu.ap.projectteambisfits.category.PrimaryCategoryService;
import edu.ap.projectteambisfits.emailtemplate.EmailTemplateService;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PrimaryCategoryService primcatservice;

    @Autowired
    private EmailTemplateService emailTemplateService;

    private String findCategoryEmail(String categoryid) {
        for (PrimaryCategory primcat : primcatservice.findAll()) {
            for (Category category : primcat.getCategoryList()) {
                if (categoryid.equalsIgnoreCase(category.getId())) {
                    return primcat.getEmailadress();
                }
            }
        }
        return null;
    }

    public void sendDefectCreatedEmail(String defectid, String defectname, String defectCategoryId)
            throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(msg, true);
        String body;

        body = emailTemplateService.findByName("Defect created").getText();
        body = body.replace("[[defect]]", defectname);
        StringBuilder bodywithurl = new StringBuilder(body);
        bodywithurl.append("<br/>Link to defect: vps101.ap.be/defect/" + defectid);
        helper.setSubject("New defect added.");
        helper.setTo(findCategoryEmail(defectCategoryId));
        helper.setText(bodywithurl.toString(), true);

        javaMailSender.send(msg);
    }

    public void sendTaskCreatedEmail(String taskid, String taskname, String taskCategoryId) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(msg, true);
        String body;

        body = emailTemplateService.findByName("Task created").getText();
        body = body.replace("[[task]]", taskname);
        StringBuilder bodywithurl = new StringBuilder(body);
        bodywithurl.append("<br/>Link to task: vps101.ap.be/task/" + taskid);

        helper.setSubject("New task added.");
        helper.setTo(findCategoryEmail(taskCategoryId));
        helper.setText(bodywithurl.toString(), true);

        javaMailSender.send(msg);
    }

    public boolean validateEmailAdress(String email) {
        try {
            InternetAddress adress = new InternetAddress(email);
            adress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    public void sendEmailForExternalFirm(String emailbody, String recipient, String emailsubject, String[] carboncopies)
            throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(msg, true);

        for (int i = 0; i < carboncopies.length; i++) {
            if (validateEmailAdress(carboncopies[i])) {
                InternetAddress adress = new InternetAddress(carboncopies[i]);
                msg.addRecipient(Message.RecipientType.CC, adress);
            }
        }
        helper.setSubject(emailsubject);
        helper.setTo(recipient);
        helper.setText(emailbody, true);

        javaMailSender.send(msg);
    }
}