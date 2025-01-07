package com.randomaccess;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	// Private static map keyed by a long that represents the id of a record, the starting file pointer position
	// of the stored record in the file.
	private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();

	// Private static int to keep track of how many records are in the file.
	private static int recordsInFile = 0;

	// static initializer to load indexes
	static {
		try (RandomAccessFile rb = new RandomAccessFile("student.idx", "r");) {
			loadIndex(rb, 0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {

		//BuildStudentData.build("student", true);

		try (RandomAccessFile ra = new RandomAccessFile("student.dat", "r")) {
			//loadIndex(ra, 0);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter a Student Id or 0 to quit:");
			while (sc.hasNext()) {
				long studentId = Long.parseLong(sc.nextLine());
				if (studentId < 1) {
					break;
				}
				if (studentId  > recordsInFile) {
					System.out.println("Invalid Student Id. Exiting.");
					break;
				}
				ra.seek(indexedIds.get(studentId));
				String targetedRecord = ra.readUTF();
				System.out.println(targetedRecord);
				System.out.println("Enter another Student Id or 0 to quit: ");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void loadIndex (RandomAccessFile ra, int indexPosition) {
		try {
			ra.seek(indexPosition);
			recordsInFile = ra.readInt();
			System.out.println(recordsInFile);

			for (int i = 0; i < recordsInFile; i++) {
				indexedIds.put(ra.readLong(), ra.readLong());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
