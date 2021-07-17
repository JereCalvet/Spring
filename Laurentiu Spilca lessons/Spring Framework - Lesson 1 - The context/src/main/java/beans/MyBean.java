package beans;

/*
Una bean es una clase normal la cual sus instancias van a ser puestas en el contexto.
*/

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
La anotación @component no permite instanciar atributos de la clase (utiliza el constructor por defecto),
por lo tanto cuando se instancie, si los atributos no tienen valores por defecto, serán null. En este caso
el campo text seria null.
Para solucionar esto, se utiliza la anotación @PostConstruct en el método init() y se inicializan los valores.
Idea mia, también se podría poner un instance initializer block o inicializarlos en el constructor por defecto.

Nota sobre @PostConstruct: Esta anotación solo funciona siempre y cuando el bean sea parte del contexto
de spring. Si creo un objeto de esta clase fuera del mismo, el valor de text sera null. (para probar crear una
instancia en el método main fuera de spring)
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
