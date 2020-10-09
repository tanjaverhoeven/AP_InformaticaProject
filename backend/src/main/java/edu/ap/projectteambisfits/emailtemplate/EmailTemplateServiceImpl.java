package edu.ap.projectteambisfits.emailtemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

  @Autowired
  private EmailTemplateRepository emailTemplateRepository;

  @Override
  public List<EmailTemplate> findAll() {
    return emailTemplateRepository.findAll();
  }

  @Override
  public EmailTemplate getEmailTemplate(String id) {
    return emailTemplateRepository.findById(id).get();
  }

  @Override
  public void deleteEmailTemplate(EmailTemplate emailTemplate) {
    emailTemplateRepository.delete(emailTemplate);
  }

  @Override
  public EmailTemplate saveEmailTemplate(EmailTemplate emailTemplate) {
    return emailTemplateRepository.save(emailTemplate);
  }

  @Override
  public EmailTemplate findByName(String name) {
    return emailTemplateRepository.findByName(name);
  }
}