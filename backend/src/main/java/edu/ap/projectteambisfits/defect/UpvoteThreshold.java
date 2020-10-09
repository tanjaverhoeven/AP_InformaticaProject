package edu.ap.projectteambisfits.defect;

public class UpvoteThreshold {
    public static int FIRST_THRESHOLD = 5;
    public static int SECOND_THRESHOLD = 10;

    public static void setFirstThreshold(int firstTh) {
        FIRST_THRESHOLD = firstTh;
    }

    public static void setSecondThreshold(int secondTh) {
        SECOND_THRESHOLD = secondTh;
    }
}