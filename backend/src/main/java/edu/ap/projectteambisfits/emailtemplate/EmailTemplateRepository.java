package edu.ap.projectteambisfits.emailtemplate;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailTemplateRepository extends MongoRepository<EmailTemplate, String> {
    public EmailTemplate findByName(String name);
}