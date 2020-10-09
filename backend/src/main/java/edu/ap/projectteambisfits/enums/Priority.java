package edu.ap.projectteambisfits.enums;

public enum Priority {
    LOW(1, 5), 
    MEDIUM(2, 10), 
    HIGH(3, -1);

    private final int priorityNumber;
    private final int threshold; 

    Priority(int priorityNumber, int threshold){
        this.priorityNumber = priorityNumber;
        this.threshold = threshold;
    }

    public int getPriorityNumber(){
        return this.priorityNumber;
    }

    public int getThreshold(){
        return this.threshold;
    }
}