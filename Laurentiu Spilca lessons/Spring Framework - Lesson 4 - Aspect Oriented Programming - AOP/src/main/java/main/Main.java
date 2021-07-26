package main;

import configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.DemoService;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            final DemoService service = context.getBean(DemoService.class);
            service.printHello("Jere");
            service.helloThrowingException();
        }
    }
}
