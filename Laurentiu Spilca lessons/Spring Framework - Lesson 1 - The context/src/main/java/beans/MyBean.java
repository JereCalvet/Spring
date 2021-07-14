package beans;

/*
Una bean es una clase normal la cual sus instancias van a ser puestas en el contexto.
*/

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
La anotacion @component no permite instanciar atributos de la clase (utiliza el constructor por defecto),
por lo tanto cuando se instancie, si los atributos no tienen valores por defecto, seran null. En este caso
el campo text seria null.
Para solucionar esto, se utiliza la anotacion @PostConstruct en el metodo init() y se inicializan los valores.
Idea mia, tambien se podria poner un instance initializer block o inicializarlos en el constructor por defecto.

Nota sobre @PostConstruct: Esta anotacion solo funciona siempre y cuando el bean sea parte del contexto
de spring. Si creo un objecto de esta clase fuera del mismo, el valor de text sera null. (para probar crear una
instancia en el metodo main fuera de spring)
*/

@Component
public class MyBean {
    private String text;

    /*
    {
        text = "Hello";
    }
    */
    @PostConstruct
    private void init() {
        text = "HELLO";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void printTextThreeTimes() {
        System.out.println(text);
        System.out.println(text);
        System.out.println(text);
    }
}
