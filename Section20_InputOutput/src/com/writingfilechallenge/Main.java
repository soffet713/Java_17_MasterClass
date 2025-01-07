package com.writingfilechallenge;

import com.student.Course;
import com.student.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		String header = """
				studentId,demographics,countryCode,enrolledMonth,enrolledYear,ageAtEnrollment,gender,\
				previousProgrammingExperience,engagement,courseCode,engagementType,enrollmentMonth,enrollmentYear""";

		Course jmc = new Course("JMC", "Java Masterclass");
		Course pymc = new Course("PYC", "Python Masterclass");
		Course jpmc = new Course("JPMC", "Japanese Masterclass");
		Course fsmc = new Course("FSMC", "Film Study Masterclass");
		List<Student> students = Stream
				.generate(() -> Student.getRandomStudent(jmc, pymc, jpmc, fsmc))
				.limit(1000)
				.toList();

		try (PrintWriter writer = new PrintWriter("writingchallenge.json")) {
			String[] headerArray = header.split(",");
			writer.printf("[%n");
			for (Student student : students) {
				int count = 0;
				writer.printf(("\t{%n\t\t\"%s\": %d,%n\t\t\"%s\": {%n\t\t\t\"%s\": \"%s\",%n\t\t\t\"%s\": %d,%n\t\t\t\"%s\": " +
							   "%d,%n\t\t\t\"%s\": %d,%n\t\t\t\"%s\": \"%s\",%n\t\t\t\"%s\": %s%n\t\t},%n")
						.formatted(
								headerArray[0],
								student.getStudentId(), // Student Id
								headerArray[1],
								headerArray[2],
								student.getCountry(), // Country Code
								headerArray[3],
								student.getEnrollmentMonth(), // Enrolled Month
								headerArray[4],
								student.getEnrollmentYear(), // Enrolled Year
								headerArray[5],
								student.getEnrollmentAge(), // Age
								headerArray[6],
								student.getGender(), // Previous Experience
								headerArray[7],
								(student.hasExperience() ? "true" : "false"))); // Gender
				for (var record : student.getEngagementRecords()) {
					String[] recordData = record.split(",");
					switch(count) {
						case 0 : writer.printf(("\t\t\"%s\": [%n\t\t\t{%n\t\t\t\t\"%s\": \"%s\",%n\t\t\t\t\"%s\": \"%s\",%n" +
												"\t\t\t\t\"%s\": %d,%n\t\t\t\t\"%s\": %d%n\t\t\t},%n").formatted(
								headerArray[8],
								headerArray[9],
								recordData[7], // Course Code
								headerArray[10],
								recordData[10], // Engagement Type
								headerArray[11],
								student.getEnrollmentMonth(),	// Enrollment Month
								headerArray[12],
								student.getEnrollmentYear()	// Enrollment Year
						));
							break;
						case 3 : writer.printf(("\t\t\t{%n\t\t\t\t\"%s\": \"%s\",%n\t\t\t\t\"%s\": \"%s\",%n\t\t\t\t\"%s\": " +
												"%d,%n\t\t\t\t\"%s\": %d%n\t\t\t}%n\t\t]%n\t},%n").formatted(
								headerArray[9],
								recordData[7], // Course Code
								headerArray[10],
								recordData[10], // Engagement Type
								headerArray[11],
								student.getEnrollmentMonth(),	// Enrollment Month
								headerArray[12],
								student.getEnrollmentYear()));	// Enrollment Year
							break;
						default : writer.printf(("\t\t\t{%n\t\t\t\t\"%s\": \"%s\",%n\t\t\t\t\"%s\": \"%s\",%n\t\t\t\t\"%s\": " +
												 "%d,%n\t\t\t\t\"%s\": %d%n\t\t\t}, %n").formatted(
								headerArray[9],
								recordData[7], // Course Code
								headerArray[10],
								recordData[10], // Engagement Type
								headerArray[11],
								student.getEnrollmentMonth(),	// Enrollment Month
								headerArray[12],
								student.getEnrollmentYear()));	// Enrollment Year
							break;
					}
					count++;
				}
			}
			writer.print("]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
