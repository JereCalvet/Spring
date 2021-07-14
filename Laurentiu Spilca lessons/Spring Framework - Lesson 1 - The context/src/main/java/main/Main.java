package main;

import beans.AutowiredBean;
import beans.MyBean;
import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    /*
    El contexto es una coleccion de instancias reconocidas por el framework.

    El contexto de la aplicacion debe ser configurado y se puede configurado usando: XML o Annotations.
    Para configurarlo debo pasarle una clase de configuración como parametro en el constructor (una clase normal
    anotada como configuracion).

    La clase AnnotationConfigApplicationContext es una implementacion de la interfaz Application context. Solo recibe
    clases marcadas con la anotacion (NO XML).
    */

    public static void main(String[] args) {
        try (final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {

            /*
            Errores pidiendo beans al contexto:
                *Si no agrego el bean a la configuracion del contexto,
                me da excepcion NoSuchBeanDefinitionException: No qualifying bean of type 'beans.MyBean' available.
                *Si hay mas de una instancia del mismo tipo, spring no sabe cual devolver y tira exception,
                NoUniqueBeanDefinitionException: No qualifying bean of type 'beans.MyBean' available: expected
                single matching bean but found 2: myBean1,myBean2
                Para solucionarlo: puedo pedir la bean por nombre o anotar la que quiero por defecto con @Primary


            Si pido el bean varias veces, solo obtengo diferentes referencias del mismo objeto, dado que por defecto las beans
            son singleton. Tambien pueden ser prototipos pero hay que configurarlo.
            */

            /*Pidiendo beans al contexto por tipo (parametro del metodo: nombre de la clase)*/
            final MyBean bean1 = context.getBean(MyBean.class);
            final MyBean bean2 = context.getBean(MyBean.class);
            final MyBean bean3 = context.getBean(MyBean.class);
            System.out.println("Hacen referencia al mismo objeto (singleton)");
            System.out.println("bean1 = " + bean1.getText());
            System.out.println("bean2 = " + bean2.getText());
            System.out.println("bean3 = " + bean3.getText());

            /*Pidiendo beans al contexto por nombre (parametro del metodo: nombre del bean)
            System.out.println("");
            final MyBean bean4 = context.getBean("myBean1", MyBean.class);
            final MyBean bean5 = context.getBean("myBean2",MyBean.class);
            final MyBean bean6 = context.getBean("myBean1",MyBean.class);
            System.out.println("bean4 = " + bean4.getText());
            System.out.println("bean5 = " + bean5.getText());
            System.out.println("bean6 = " + bean6.getText());
            */

            System.out.println();
            final AutowiredBean hostBean = context.getBean(AutowiredBean.class);
            hostBean.callingMethodOfAnAutowiredBean();

        }
    }
}
