package edu.ap.projectteambisfits.department;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
public class Contact {
    private String name;
    private String contactfunction;
    private String telephonenumber;
    private String mobilenumber;
    private String emailadress;
    @Id
    private String id;

    public Contact(String name, String contactfunction, String telephonenumber, String mobilenumber,
            String emailadress) {
        this.name = name;
        this.contactfunction = contactfunction;
        this.telephonenumber = telephonenumber;
        this.mobilenumber = mobilenumber;
        this.emailadress = emailadress;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactfunction(String contactfunction) {
        this.contactfunction = contactfunction;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getContactfunction() {
        return contactfunction;
    }
}
