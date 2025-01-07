package com.pirategamechallenge.coursegame;

import java.util.function.Predicate;

public record CourseGameAction(char key, String prompt, Predicate<Integer> action) {
}
