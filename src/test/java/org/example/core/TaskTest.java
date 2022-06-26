package org.example.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.junit.Assert.assertEquals;

public class TaskTest {
    private static final ObjectMapper MAPPER = newObjectMapper();
    final Task task = new Task("learn angular", "2023-05-30");

    void setUp() {
    }

    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(getClass().getResource("/fixtures/Task.json"), Task.class));

        assertEquals(expected, MAPPER.writeValueAsString(task));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertEquals(task, MAPPER.readValue(getClass().getResource("/fixtures/Task.json"), Task.class));
    }
}
