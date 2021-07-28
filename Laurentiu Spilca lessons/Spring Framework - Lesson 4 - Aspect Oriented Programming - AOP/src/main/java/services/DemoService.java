package services;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public void printHello(String name) {
        System.out.println(String.format("Hello %s", name));
    }

    public void helloThrowingException() {
        System.out.println("A punto de tirar una excepción");
        throw new RuntimeException("Excepción de prueba.");
    }


    public String printAndReturnHello(String name) {
        final String message = String.format("Hello %s", name);
        System.out.println("Ejecución normal del método: " + message);
        return message;
    }
}
