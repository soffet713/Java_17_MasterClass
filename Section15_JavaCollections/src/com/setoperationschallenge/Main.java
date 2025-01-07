package com.setoperationschallenge;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Task> allTasks = TaskData.getTasks("All");
        sortAndPrint("All Tasks", allTasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);

        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        sortAndPrint("Bob's Tasks", bobsTasks);

        Set<Task> carolsTasks = TaskData.getTasks("Carol");
        sortAndPrint("Carol's Tasks", carolsTasks);

        List<Set<Task>> taskSets = new ArrayList<>(List.of(annsTasks,bobsTasks,carolsTasks,allTasks));
        Set<Task> taskUnion = getUnion(taskSets);
        sortAndPrint("Full Task List", taskUnion);

        List<Set<Task>> assignedSets = new ArrayList<>(List.of(annsTasks,bobsTasks,carolsTasks));
        Set<Task> assignedTasks = getUnion(assignedSets);
        sortAndPrint("Assigned Tasks", assignedTasks);

        Set<Task> missingTasks = getDifference(taskUnion,allTasks);
        sortAndPrint("Missing Tasks", missingTasks);

        Set<Task> unassignedTasks = getDifference(allTasks,assignedTasks);
        sortAndPrint("Unassigned Tasks", unassignedTasks, sortByPriority);

        Set<Task> assignedMoreThanOnce = getUnion(List.of(getIntersect(annsTasks,bobsTasks),
                getIntersect(carolsTasks,bobsTasks),getIntersect(annsTasks,carolsTasks)));
        sortAndPrint("Tasks Assigned More Than Once", assignedMoreThanOnce, sortByPriority);

        List<Task> overlapping = new ArrayList<>();
        for(Set<Task> set : assignedSets) {
            Set<Task> dupes = getIntersect(set, assignedMoreThanOnce);
            overlapping.addAll(dupes);
        }

        Comparator<Task> priorityNatural = sortByPriority.thenComparing(Comparator.naturalOrder());
        sortAndPrint("Overlapping", overlapping, priorityNatural);
    }

    private static Set<Task> getUnion(List<Set<Task>> taskSets) {
        Set<Task> unionSet = new HashSet<Task>();
        for(Set<Task> taskSet : taskSets) {
            unionSet.addAll(taskSet);
        }
        return unionSet;
    }

    private static Set<Task> getIntersect(Set<Task> A, Set<Task> B) {
        Set<Task> intersectAB = new HashSet<Task>(A);
        intersectAB.retainAll(B);
        return intersectAB;
    }

    private static Set<Task> getDifference(Set<Task> A, Set<Task> B) {
        Set<Task> AMinusB = new HashSet<Task>(A);
        AMinusB.removeAll(B);
        return AMinusB;
    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {
        String lineSeparator = "-".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }
}
