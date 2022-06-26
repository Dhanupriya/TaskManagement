package org.example.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.example.core.Task;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;

public class TaskDAO extends AbstractDAO<Task> {
    public TaskDAO(SessionFactory factory) {
        super(factory);
    }

    public Task findById(Long id) {
        return get(id);
    }

    public long create(Task task) {
        return persist(task).getId();
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("org.example.core.Task.findAll"));
    }

    public void completeTasks(List<Long> taskIds) {
        taskIds.stream().forEach(id -> {
            Task task = findById(id);
            currentSession().delete(task);
        });
    }
}
