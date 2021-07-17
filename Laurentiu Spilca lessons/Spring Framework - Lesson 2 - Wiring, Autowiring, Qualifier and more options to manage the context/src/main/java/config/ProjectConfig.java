package config;

import beans.Cat;
import beans.Owner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "beans")
@Configuration
public class ProjectConfig {
    /*
      Formas de conectar beans, cuando se utiliza la creación a través de métodos anotados con @bean:
        a) Llamando el método constructor del bean. Spring va a mirar en el contexto si existe una instancia,
           si no hay, la crea normalmente. Caso contrario, al ser beans no va a volver a crear la instancia,
           simplemente la toma del contexto. Esto sucede porque son singletons. Posteriormente hace el wiring, la conexión.
        b) Poniendo otras beans de parámetros en el método constructor del bean.
           Los métodos anotados con @bean que tienen parámetros de otras beans fuerzan a spring a buscar
           una instancia en el contexto, sino la crea, y la inyecta dentro haciendo el wiring.

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

    /*
      Formas de conectar beans, cuando se utiliza la creación a través de
      anotaciones estereotipo (@component, @service, @repository, etc):
        a) inyectándola directamente sobre el field
            @Autowired
            private MyBean bean;
        b) injertándola en el parámetro del constructor
            private final MyBean bean;
            @Autowired
            public AutowiredBean(MyBean bean) {
                this.bean = bean;
            }
        c) injertándola en el parámetro del setter
            private MyBean bean;
            @Autowired
            public void setBean(MyBean bean) {
                this.bean = bean;
            }
       */

    /*
    Autowired:
        *Puede utilizar como parámetro, required: false, en caso que la instancia no exista para evitar la excepción.
         No es recomendado porque el field tendría un null y trabajar con nulls esta mal desde el punto de vista
         del código limpio. Ej: Autowired(required = false)
        Posibles errores:
            *Si no tengo una instancia en el contexto, me da excepción: NoSuchBeanDefinitionException.
            *Si tengo mas de una instancia del mismo tipo, creadas con método @bean (única forma que esta excepción suceda),
             el contexto no sabe cual conectar y tira error NoUniqueBeanDefinitionException: No qualifying bean of type
             'beans.Cat' available: expected single matching bean but found 2: cat1,cat2.
             Soluciones: 
                A) anotar algún método @bean con @primary. Este tipo de inyección se llama inyección por tipo.
                B) anotar las beans y el @autowired con la anotación @Qualifier("nombre del bean"). Este tipo de inyección
                se llama inyección por nombre.
*/
    @Bean
    @Qualifier("CatA")
    public Cat cat1() {
        final Cat cat = new Cat();
        cat.setName("CatA");
        return cat;
    }

    @Bean
    @Qualifier("CatB")
    public Cat cat2() {
        final Cat cat = new Cat();
        cat.setName("CatB");
        return cat;
    }
}
