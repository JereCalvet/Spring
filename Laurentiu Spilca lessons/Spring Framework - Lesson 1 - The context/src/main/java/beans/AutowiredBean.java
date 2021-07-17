package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredBean {
    /*
        La anotación autowired sirve para conectar beans entre en el contexto.
        Hay 3 formas de conectar beans:
            a) inyectándola directamente sobre el field
                @Autowired
                private MyBean bean;

            b) inyectándola en el parámetro del constructor
                private final MyBean bean;
                @Autowired
                public AutowiredBean(MyBean bean) {
                    this.bean = bean;
                }

            c) inyectándola en el parámetro del setter
                private MyBean bean;
                @Autowired
                public void setBean(MyBean bean) {
                    this.bean = bean;
                }

        La opción recomendada es la B, sobre el constructor, dado que es la única opción que
        permite hacer el field final, convirtiendo en una constante, es decir inmutable.
        Ademas tiene beneficios a la hora de hacer testing.
*/

    private final MyBean bean;

    @Autowired
    public AutowiredBean(MyBean bean) {
        this.bean = bean;
    }

    public void callingMethodOfAnAutowiredBean() {
        System.out.println("Calling method 'print text 3 times' of the autowired bean");
        bean.printTextThreeTimes();
    }
}
