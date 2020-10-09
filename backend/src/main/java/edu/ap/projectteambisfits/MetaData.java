package edu.ap.projectteambisfits;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Instant;

public class MetaData {
    protected String creatorid;
    protected LocalDateTime date;

    public MetaData(String creatorid) {
        this.creatorid = creatorid;
        this.date = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Brussels"));
    }

    public String getcreatorid() {
        return creatorid;
    }

    public LocalDateTime getDate() {
        return date;
    }
}