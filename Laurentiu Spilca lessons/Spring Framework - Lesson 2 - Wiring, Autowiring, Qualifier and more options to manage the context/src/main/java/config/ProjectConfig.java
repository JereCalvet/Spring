package config;

import beans.Cat;
import beans.Owner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "beans")
@Configuration
public class ProjectConfig {
    /*
      Formas de conectar beans, cuando se utiliza la creación a traves de metedos anotados con @bean:
        a) Llamando el metodo constructor del bean. Spring va a mirar en el contexto si existe una instancia,
           si no hay, la crea normalmente. Caso contrario, al ser beans no va a volver a crear la instancia,
           simplemente la toma del contexto. Esto sucede porque son singletons. Posteriormente hace el wiring, la conección.
        b) Poniendo otras beans de parametros en el metodo contructor del bean.
           Los metodos anotados con @bean que tienen parametros de otras beans fuerzan a spring a buscar
           una instacia en el contexto, sino la crea, y la injecta dentro haciedo el wiring.

*/
    /*a)
    @Bean
    public Cat cat() {
        final Cat cat = new Cat();
        cat.setName("Mauricio");
        return cat;
    }

    @Bean
    public Owner owner() {
        final Owner owner = new Owner();
        owner.setCat(cat());
        return owner;
    }*/

    /*b)
    @Bean
    public Cat cat() {
        final Cat cat = new Cat();
        cat.setName("Mauricio");
        return cat;
    }

    @Bean
    public Owner owner(Cat cat) {
        final Owner owner = new Owner();
        owner.setCat(cat);
        return owner;
    }*/
}
