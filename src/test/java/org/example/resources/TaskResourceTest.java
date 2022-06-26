package org.example.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.example.core.Task;
import org.example.db.TaskDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TaskResourceTest {

    private static final TaskDAO taskDAO = mock(TaskDAO.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new TaskResource(taskDAO))
            .build();
    private org.example.core.Task task;
    private List<Task> taskList = new ArrayList<>();

    @BeforeEach
    void setup() {
        task = new Task();
        taskList.add(task);
    }

    @AfterEach
    void tearDown() {
        reset(taskDAO);
    }

    @Test
    void viewTasksTest() {
        when(taskDAO.findAll()).thenReturn(taskList);
        Response response = EXT.target("/taskManagement/viewTasks").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
        verify(taskDAO).findAll();
    }

    @Test
    void addTaskTest() {
        when(taskDAO.create(any(Task.class))).thenReturn(1L);
        Response response = EXT.target("/taskManagement/addTask").request().post(Entity.json(task));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
        verify(taskDAO).create(any(Task.class));
    }

    @Test
    void completeTaskTest() throws JsonProcessingException {
        doNothing().when(taskDAO).completeTasks(anyList());
        ObjectMapper mapper = new ObjectMapper();
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        String requestJson = mapper.writeValueAsString(idList);
        Response response = EXT.target("/taskManagement/completeTask").request().post(Entity.json(requestJson));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatusInfo().getStatusCode());
        verify(taskDAO).completeTasks(anyList());
    }
}