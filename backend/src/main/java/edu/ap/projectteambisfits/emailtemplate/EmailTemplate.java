package edu.ap.projectteambisfits.emailtemplate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "emailTemplates")
public class EmailTemplate {
  @Id
  private String id;
  private String name;
  private String text;

  public EmailTemplate(String name, String text) {
    this.name = name;
    this.text = text;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}