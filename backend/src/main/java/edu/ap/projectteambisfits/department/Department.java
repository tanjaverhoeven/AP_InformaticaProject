package edu.ap.projectteambisfits.department;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")
public class Department {
    private String name;
    private List<Contact> contactlist;
    @Id
    private String id;

    @JsonCreator
    public Department(@JsonProperty("name") String name) {
        this.name = name;
        this.contactlist = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Contact> getContactlist() {
        return contactlist;
    }

    public void addContactToList(Contact contact) {
        this.contactlist.add(contact);
    }

    public void removeContactFromList(String contactname) {
        for (int i = 0; i < this.contactlist.size(); i++) {
            if (this.contactlist.get(i).getName().equals(contactname)) {
                this.contactlist.remove(i);
            }
        }
    }

    public void updateContactList(String contactid, Contact newContact) {
        for (int i = 0; i < this.contactlist.size(); i++) {
            if (this.contactlist.get(i).getId().equals(contactid)) {
                this.contactlist.get(i).setName(newContact.getName());
                this.contactlist.get(i).setContactfunction(newContact.getContactfunction());
                this.contactlist.get(i).setTelephonenumber(newContact.getTelephonenumber());
                this.contactlist.get(i).setMobilenumber(newContact.getMobilenumber());
                this.contactlist.get(i).setEmailadress(newContact.getEmailadress());
            }
        }
    }
}
