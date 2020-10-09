package edu.ap.projectteambisfits.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.ap.projectteambisfits.person.Person;

@Document(collection = "user")
public class User extends Person implements Observer {

    public enum Genders {
        MALE, FEMALE, X
    };

    private String spEmail;
    private Genders gender;
    private String role;
    private List<String> upvoted;
    private List<Notification> notifications;

    // #region Getters

    public Genders getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getSpEmail() {
        return spEmail;
    }

    // #endregion
    // #region Setters
    public void setGender(String input) {
        Genders gender = Genders.X;

        if (input == "Male" || input == "M") {
            gender = Genders.MALE;
        } else if (input == "Female" || input == "F") {
            gender = Genders.FEMALE;
        } else {
            gender = Genders.X;
        }
        this.gender = gender;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSpEmail(String spEmail) {
        this.spEmail = spEmail;
    }

    // #endregion
    // #region Notification & upvotes

    public List<String> getUpvoted() {
        return upvoted;
    }

    public List<Notification> getNotifications() {
        return this.notifications;
    }

    public void setUpvoted(List<String> upvoted) {
        this.upvoted = upvoted;
    }

    public void addUpvoteToUpvotedDefects(String id) {
        this.getUpvoted().add(id);
    }

    @Override
    public void receiveUpdate(String defectId) {
        this.notifications.add(new Notification(defectId));
    }

    // #endregion

    public User(String firstname, String lastname, String email, Genders gender, String role, String spEmail) {
        super(firstname, lastname, email, false);
        this.gender = gender;
        this.role = role;
        this.notifications = new ArrayList<Notification>();
        this.upvoted = new ArrayList<String>();
        this.spEmail = spEmail;
    }

    @Override
    public String toString() {
        return super.getFirstname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }
}