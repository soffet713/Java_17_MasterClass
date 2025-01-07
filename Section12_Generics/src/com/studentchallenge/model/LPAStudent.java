package com.studentchallenge.model;

import java.util.Comparator;

public class LPAStudent extends Student {

    private double percentComplete;
    private static String[] lpaCourses = {"C++","Java","Python"};

    public LPAStudent() {
        percentComplete = random.nextDouble(0,100.001);
        checkCourse();
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    public void checkCourse() {
        boolean checkCourse = false;
        for(String c : lpaCourses) {
            checkCourse = super.getCourse().compareTo(c)==0;
        }
        if(!checkCourse) {
            super.setCourse(lpaCourses[random.nextInt(3)]);
        }
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        if(fieldName.equalsIgnoreCase("percentComplete")){
            return percentComplete <= Double.parseDouble(value);
        }

        return super.matchFieldValue(fieldName, value);
    }

    @Override
    public String toString() {
        return "%s | %8.1f%%".formatted(super.toString(), percentComplete);
    }
}

class LPAStudentComparator implements Comparator<LPAStudent> {

    @Override
    public int compare(LPAStudent s1, LPAStudent s2) {
        return (int)(s1.getPercentComplete() - s2.getPercentComplete());
    }
}
