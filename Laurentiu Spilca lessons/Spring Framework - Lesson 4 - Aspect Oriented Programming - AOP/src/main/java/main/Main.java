package main;

import configuration.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.DemoService;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            final DemoService service = context.getBean(DemoService.class);
            service.printHello("Jere");
            /*service.helloThrowingException();  comento para poder seguir trabajando*/
            System.out.println("\nA countinuación voy a utilizar la anotación @Around con otro método a interceptar.");
            final String mensaje = service.printAndReturnHello("Jere");
            System.out.println("Valor final devuelto = " + mensaje);
        }
    }
}
