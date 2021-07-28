package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
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
    El point cut (método "Y") se puede especificar con una anotación o con una sintaxis de expresión del lenguaje.
    En caso de especificar con una anotación, esta misma debera tener la retención policy: runtime (@Retention(RetentionPolicy.RUNTIME)).
    Esta política de retención no es el valor por defecto de las anotaciones.
    */

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

    /*Esta anotación permite controlar el flujo del método a interceptar.
    Se puede añadir lógica, decorar el método interceptado, manipular los argumentos,
    obtener t0do tipo de info del método interceptado, NO ejecutar el método interceptado y hacer otra cosa,
    ejecutar el metodo interceptado multiples veces, etc.
    Las posibilidades son muchas pero es recomendable solo decorar el método y no cambiar el flujo
    de la aplicación, es decir no reemplazar la lógica de la misma, simplemente para mantener el código limpio.
    Los aspectos se pueden utilizar para logging, transacciones, seguridad.
    */
    @Around("execution(* services.DemoService.printAndReturnHello(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("--Hacer algo antes del método interceptado--");
        Object result = null;
        try {
            if (1 == 1) {
                System.out.println("--Ejecuto el método interceptado, si cumple \"x\" condición--");
                //result = joinPoint.proceed(); ejecución normal
                result = joinPoint.proceed(new Object[]{"Donato --nuevo argumento--"}); //ejecución cambiando argumentos
                System.out.println("--Hacer algo después del método interceptado--");

                final Object[] obtengoArgumentos = joinPoint.getArgs();
                final Signature obtengoSignature = joinPoint.getSignature();

            }
                System.out.println("--Este es el valor actual del método interceptado, tras alterar los argumentos: " + result + "--");
        } catch (Throwable throwable) {
            System.out.println("--Aquí estaría implementado alguna funcionalidad si falla el método interceptado.");
            throwable.printStackTrace();
        }
        result = "cambiando el return del método interceptado en el aspecto.";
        return result;
    }


}
