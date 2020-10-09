package edu.ap.projectteambisfits.emailtemplate;

import java.util.List;

public interface EmailTemplateService {
  List<EmailTemplate> findAll();

  void deleteEmailTemplate(EmailTemplate emailTemplate);

  EmailTemplate saveEmailTemplate(EmailTemplate emailTemplate);

  EmailTemplate getEmailTemplate(String id);

  EmailTemplate findByName(String name);
}