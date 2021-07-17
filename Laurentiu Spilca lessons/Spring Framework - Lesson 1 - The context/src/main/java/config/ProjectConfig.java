package config;

import beans.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
En un proyecto se pueden utilizar muchas configuraciones y estas pueden estar
hechas con xml, con la anotación o ambas.

Formas de crear instancias de los beans y ponerlas en el contexto:
    a) anotando un método con @bean.
       Es buena practica nombrar el método solo con el nombre del bean y sin un verbo.

    b) Utilizar la anotación de estereotipo @component.
         Pasos:
         1) anotar la clase con @component.
         2) anotar la configuración del contexto con la anotación @ComponentScan()
            y pasarle como parámetro el paquete donde tiene que buscar la clase. Esto es necesario
            xq spring no busca automáticamente las beans, entonces si la declaro y no la busco en el
            paquete, cuando la llame me va a dar NoSuchBeanDefinitionException.

    La diferencia principal en las formas de crear instancias es que con @bean se puede crear
    MAS DE UNA instancia del mismo tipo, mientras que con @component se crea UNA ÚNICA instancia.
*/

@Configuration
@ComponentScan(basePackages = "beans")
public class ProjectConfig {

   /*
    a) creando instancias anotando método con @bean

    ---------
    A esta anotación (@bean) se le puede dar un nombre custom y utilizar ese nombre para llamar este bean desde el contexto:
      @Bean("nombreCustom")
      context.getBean("nombreCustom",MyBean.class);
    @Bean
    public MyBean myBean1() {
        *//*Esta instancia se pone automáticamente en el contexto, gracias a la anotación @bean*//*
        final MyBean myBean = new MyBean();
        myBean.setText("Hello");
        return myBean;
    }

    @Bean
    @Primary //(resuelve NoUniqueBeanDefinitionException, leer clase main linea 24)
    public MyBean myBean2() {
        final MyBean myBean = new MyBean();
        myBean.setText("World");
        return myBean;
    }*/
}
