package aspects;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
Un aspecto es una porción de código desacoplada que se utiliza para interceptar métodos y agregar
funcionalidades. Sirven para realizar tareas que no tienen que ver con la lógica del negocio.
Hay que tener en cuenta que complica el código a la hora de debugear ya que, después de la ejecución del método
interceptado, se ejecutará el aspecto y este no va a aparecer en el debug.
Una buena forma de llamarlos es, de la misma manera que se llama la clase donde esta el método a interceptar.

Pasos para configurar aspectos:
   A) Habilitar los aspectos en la configuración del contexto con la anotación @EnableAspectJAutoProxy.
   B) Crear los beans del aspecto y agregarlos al contexto, dado que @Aspect no es una anotación de estereotipo.
*/

@Aspect
@Component
public class HelloServiceAspect {

    /*
    Las anotaciones permiten ejecutar "X" método cuando "Y" método sea interceptado.
    Reciben como parámetro, el método a interceptar ("Y" point cut) y la anotación determinara el punto de encuentro (join point),
    es decir, el momento en que se ejecutará.
    El point cut (método "Y") se puede especificar con una anotación o con una sintaxis de expresión del lenguaje.*/


    /*Esta anotación permite ejecutar el código antes del método interceptado*/
    @Before("execution(* services.DemoService.printHello(..))")
    public void before() {
        System.out.println("--Aspecto ejecutándose ANTES del método interceptado--");
    }

    /*Esta anotación permite ejecutar el código después del método interceptado*/
    @After("execution(* services.DemoService.printHello(..))")
    public void after() {
        System.out.println("--Aspecto ejecutándose DESPUÉS del método interceptado--");
    }

    /*Esta anotación permite ejecutar el código, si el método interceptado, no devolvió ninguna excepción.
    * Es decir, se ejecuto de manera exitosa. */
    @AfterReturning("execution(* services.DemoService.printHello(..))")
    public void afterReturning() {
        System.out.println("--Aspecto ejecutándose debido a que, la ejecución el método interceptado fue exitosa--");
    }

    /*Esta anotación permite ejecutar el código, si el método interceptado, devolvió una excepción.
    * Es decir, se produjo un error. */
    @AfterThrowing("execution(* services.DemoService.helloThrowingException(..))")
    public void afterThrowing() {
        System.out.println("--Aspecto ejecutándose debido a que, la ejecución el método interceptado tiro una excepción--");
    }


    //https://youtu.be/BVk54NRRFsY?list=PLEocw3gLFc8WO_HvFzTWUj2fqa7Y8-yg5&t=2194
}
