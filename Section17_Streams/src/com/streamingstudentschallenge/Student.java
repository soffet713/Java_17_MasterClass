package com.streamingstudentschallenge;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Student {

    private static long lastStudentId = 1;
    private final static Random random = new Random();
    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean programmingExperience;
    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean programmingExperience,
                   Course... courses) {
        studentId = lastStudentId++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        if(courses.length>4) {
            addRandomCourses(courses);
        } else {
            for (Course course : courses) {
                addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
            }
        }
    }

    public void addRandomCourses(Course... courses) {
        int numCourses = random.nextInt(2,6);
        for(int x = 0; x < numCourses; x++) {
            boolean courseAdded = false;
            while(!courseAdded) {
                int randomIndex = random.nextInt(courses.length);
                Course currCourse = courses[randomIndex];
                if(!engagementMap.containsKey(currCourse.courseCode())) {
                    addCourse(courses[randomIndex], LocalDate.of(yearEnrolled, 1, 1));
                    courseAdded = true;
                }
            }
        }
    }

    public void addCourse(Course newCourse) {
        addCourse(newCourse, LocalDate.now());
    }

    public void addCourse(Course newCourse, LocalDate enrollDate) {
        engagementMap.put(newCourse.courseCode(), new CourseEngagement(newCourse,
                enrollDate, "Enrollment"));
    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean hasProgrammingExperience() {
        return programmingExperience;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }

    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement info = engagementMap.get(courseCode);
        return info == null ? 0 : info.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {
        int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
        for(String key : engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
        }
        return inactiveMonths;
    }

    public boolean isEnrolled(Course c) {
        return engagementMap.containsKey(c.courseCode());
    }

    public double getPercentComplete(String courseCode) {
        var info = engagementMap.get(courseCode);
        return (info==null) ? 0 : info.getPercentComplete();
    }

    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {
        var activity = engagementMap.get(courseCode);
        if(activity!=null) {
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    private static String getRandomVal(String... data) {
        return data[random.nextInt(data.length)];
    }

    public static Student getRandomStudent(Course... courses) {
        int maxYear = LocalDate.now().getYear() + 1;
        Student student = new Student(
                getRandomVal("AU","CA","CN","GB","IN","IR","JP","KR","NG","PH","SC","UA","US"),
                random.nextInt(2017,maxYear),random.nextInt(17,70),getRandomVal("M","F","U"),
                random.nextBoolean(),courses);

        for(Course c : courses) {
            int lecture = random.nextInt(30, c.lectureCount());
            int year = random.nextInt(student.getYearEnrolled(), maxYear);
            int month = random.nextInt(1,13);
            if(year == (maxYear - 1)) {
                if(month > LocalDate.now().getMonthValue()) {
                    month = LocalDate.now().getMonthValue();
                }
            }
            student.watchLecture(c.courseCode(), lecture, month, year);
        }
        return student;
    }

    @Override
    public String toString() {
        return ("Student: ID = %d | Country Code = %s | Year Enrolled = %d | Age Enrolled = %d | Gender = %s |" +
                " Programming Experience = %B | Engagement Map = %s").formatted(studentId,countryCode,yearEnrolled,
                ageEnrolled,gender,programmingExperience,engagementMap);
    }
}
