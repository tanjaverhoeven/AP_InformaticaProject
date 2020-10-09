package edu.ap.projectteambisfits.external;

public class ExternalEmailModel {

    private String body;
    private String recipient;
    private String subject;
    private String[] carboncopies;

    public ExternalEmailModel(String body, String recipient, String subject, String[] carboncopies) {
        this.body = body;
        this.recipient = recipient;
        this.subject = subject;
        this.carboncopies = carboncopies;
    }

    public String getBody() {
        return body;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String[] getCarboncopies() {
        return carboncopies;
    }
}