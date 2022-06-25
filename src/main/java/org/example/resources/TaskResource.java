package org.example.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.example.core.Task;
import org.example.db.TaskDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/taskManagement/")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private TaskDAO taskDAO;

    public TaskResource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GET
    @Path("viewTasks")
    @UnitOfWork
    public List<Task> getTaskList() {
        return taskDAO.findAll();
    }

    @POST
    @Path("addTask")
    @UnitOfWork
    public Long addTask(@Valid Task task) {
        return taskDAO.create(task);
    }

    @POST
    @Path("completeTask")
    @UnitOfWork
    public void completeTask(@Valid long[] taskIds) {
        taskDAO.completeTasks(taskIds);
    }
}