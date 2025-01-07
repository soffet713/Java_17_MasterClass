package com.setoperationschallenge;

enum TaskStatus { ASSIGNED, IN_PROGRESS, IN_QUEUE, NOT_YET_ASSIGNED; }
enum Priority { HIGH, MEDIUM, LOW; }

public class Task implements Comparable<Task> {

    private String projectName;
    private String description;
    private String assignee;
    private Priority priority;
    private TaskStatus status = TaskStatus.NOT_YET_ASSIGNED;

    public Task(String projectName, String description, Priority priority) {
        this(projectName, description, null, priority);
    }

    public Task(String projectName, String description, String assignee, Priority priority) {
        this(projectName, description, assignee,
                priority, assignee == null ? TaskStatus.NOT_YET_ASSIGNED : TaskStatus.ASSIGNED);
    }

    public Task(String projectName, String description, String assignee, Priority priority, TaskStatus status) {
        this.projectName = projectName;
        this.description = description;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        if(!getProjectName().equals(task.getProjectName())) return false;
        return getProjectName().equals(task.getProjectName()) && getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getProjectName().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "%-20s %-25s %-10s %-10s %s".formatted(projectName,description,priority,assignee,status);
    }

    @Override
    public int compareTo(Task o) {
        return (this.projectName.compareTo(o.getProjectName()) == 0) ? this.description.compareTo(o.getDescription()) :
                this.projectName.compareTo(o.getProjectName());
    }
}
