package edu.ap.projectteambisfits.external;

import org.springframework.data.mongodb.core.mapping.Document;

import edu.ap.projectteambisfits.person.Person;

@Document(collection = "external")
public class External extends Person {

    private String companyname;
    private String role;

    public External(String firstname, String lastname, String email, String companyname) {
        super(firstname, lastname, email, true);
        this.companyname = companyname;
        this.role = "EXTERNAL";
    }

    // #region GETTERS
    public String getRole() {
        return role;
    }

    public String getCompanyname() {
        return companyname;
    }
    // #endregion

}