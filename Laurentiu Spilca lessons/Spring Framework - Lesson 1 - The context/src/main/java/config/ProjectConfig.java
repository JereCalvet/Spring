package config;

import beans.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
En un proyecto se pueden utilizar muchas configuraciones y estas pueden estar
hechas con xml, con la anotacion o ambas.

Formas de crear instancias de los beans y ponerlas en el contexto:
    a) anotando un metodo con @bean.
        Es buena practica nombrar el metodo solo con el nombre del bean y sin un verbo.
    b)
*/

@Configuration
public class ProjectConfig {


    /*A esta anotacion se le puede dar un nombre custom y utilizar ese nombre para llamar este bean desde el contexto:
      @Bean("nombreCustom")
      context.getBean("nombreCustom",MyBean.class);
    */
    @Bean
    public MyBean myBean1() {
        /*Esta instancia se pone automaticamente en el contexto, gracias a la anotacion @bean*/
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
    }
}
