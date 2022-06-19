package org.example.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task")
@NamedQuery(
        name = "org.example.core.Task.findAll",
        query = "SELECT t FROM Task t"
)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Column(name = "task_date", nullable = false)
    private String taskDate;

    public Task() {
    }

    public Task(String taskDescription, String taskDate) {
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task) o;

        return id == task.id &&
                Objects.equals(taskDescription, task.taskDescription) &&
                Objects.equals(taskDate, task.taskDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskDescription, taskDate);
    }
}
