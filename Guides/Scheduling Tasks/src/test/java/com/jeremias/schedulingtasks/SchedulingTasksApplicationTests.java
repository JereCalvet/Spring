package com.jeremias.schedulingtasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SchedulingTasksApplicationTests {

    private final ScheduledTask tasks;

    @Autowired
    SchedulingTasksApplicationTests(ScheduledTask tasks) {
        this.tasks = tasks;
    }

    @Test
    void contextLoads() {
        assertThat(tasks).isNotNull();
    }

}
