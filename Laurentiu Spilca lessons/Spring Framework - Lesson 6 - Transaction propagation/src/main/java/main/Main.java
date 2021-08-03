package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {

            //   https://youtu.be/O9vrhKlGZbE?list=PLEocw3gLFc8WO_HvFzTWUj2fqa7Y8-yg5&t=1543
        }
    }
}
