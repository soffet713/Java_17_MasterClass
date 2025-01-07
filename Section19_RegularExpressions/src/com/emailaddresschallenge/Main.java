package com.emailaddresschallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		String emailList = """
				john.boy@valid.com
				john.boy@invalid
				jane.doe-smith@valid.co.uk
				bob!@invalid.com
				jane_Doe1976@valid.co.uk
				elaineinvalid1983@.com
				bob-1964@valid.net
				david@invalid..com
				elaine@valid-test.com.au
				david@valid.io
				""";

		Pattern partialPattern = Pattern.compile("([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
		Matcher emailMatcher = partialPattern.matcher(emailList);
		emailMatcher.results().forEach(mr -> {
					System.out.printf("[username=%s, domain=%s]%n",
							mr.group(1),
							mr.group(2));
				}
		);

		System.out.println("-".repeat(45));

		Pattern emailPattern = Pattern.compile(
				"([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
		String[] emailSamples = emailList.lines().toArray(String[]::new);
		for (String email : emailSamples) {
			Matcher eMatcher = emailPattern.matcher(email);
			boolean matched = eMatcher.matches();
			System.out.print(email + " is " + (matched ? "VALID " : "INVALID "));
			if (matched) {
				System.out.printf("[username=%s, domain=%s]%n",
						eMatcher.group(1),
						eMatcher.group(2));
			} else {
				System.out.println();
			}
		}
	}
}
