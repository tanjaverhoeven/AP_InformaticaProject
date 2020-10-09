package edu.ap.projectteambisfits.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.ap.projectteambisfits.MetaData;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String text;
    private String defectid;
    private MetaData metadata;
    // this param is not used but needed to prevent an error
    private String creatorid;

    public Comment(String defectid, String text, String creatorid) {
        this.defectid = defectid;
        this.text = text;
        this.metadata = new MetaData(creatorid);
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getDefectid() {
        return defectid;
    }

    public MetaData getMetaData() {
        return metadata;
    }
}