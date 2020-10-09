package edu.ap.projectteambisfits.enums;

public enum Status {
    OPEN("Open"),
    IN_PROGRESS("In progress"),
    PROCESS_BY_AP("Processing by ap"),
    PASSED_TO_PROVINCE("Passed to province coordinator"),
    EXECUTING_BY_EXTERNAL("Executing by external"),
    EXECUTING_BY_AP("Executing by AP employee"),
    CANCELED("Canceled"), 
    COMPLETE("Completed");

    private final String val;

    Status(String val){
        this.val = val;
    }

    public String getVal(){
        return this.val;
    }
}