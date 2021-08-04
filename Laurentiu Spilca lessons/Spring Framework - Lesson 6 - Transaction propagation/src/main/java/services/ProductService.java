package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProductRepository;

import java.util.stream.IntStream;

/*
Tipos de propagación:
   A) REQUIRED (Default)
      Esta propagación indica que el método marcado con @Transactional crea, SI NO EXISTE PREVIAMENTE, una
      transacción y la utiliza. En el caso que ya existiera una transacción previa, de métodos anteriores, utilizara esa misma.

   B) REQUIRES_NEW
      Esta propagación indica que el método marcado con @Transactional crea, SI O SI, una transacción y la utiliza.

   C) MANDATORY
      Esta propagación indica que el método marcado con @Transactional NO CREA ninguna transacción y para que se ejecute
      correctamente, DEBE EJECUTARSE DENTRO de una transacción, la cual deberá ser creada previamente por algún otro método.
      En caso de que la transacción necesaria no existiera, dara el runtime error:
        Exception in thread "main" org.springframework.transaction.IllegalTransactionStateException: No existing transaction
        found for transaction marked with propagation 'mandatory'

   D) NEVER
      Esta propagación indica que el método marcado con @Transactional NO CREA ninguna transacción y para que se ejecute
      correctamente, DEBE EJECUTARSE FUERA de una transacción.
      En caso de que de se llame desde una transacción, el dara runtime error:
        Exception in thread "main" org.springframework.transaction.IllegalTransactionStateException: Existing transaction
        found for transaction marked with propagation 'never'
      Se puede pensar que es lo opuesto a mandatory.

   E) SUPPORTS

   F) NOT_SUPPORTED

   G) NESTED

*/

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addTenProductsRequired() { //create tx A
        for (int i = 0; i <= 10; i++) {
            productRepository.addProductRequired("Product " + i);
            if (i == 5) {
                throw new RuntimeException("Boom");
            }
        }
    } /* runtime exception hace rollback de tx A (se pierden todos los valores).
        Total tx creadas: 1 → tx A | rollback
    */

    @Transactional(propagation = Propagation.REQUIRED)
    public void addTenProductsRequiresNew() { //create tx A
        for (int i = 1; i <= 10; i++) {
            productRepository.addProductRequiresNew("Product " + i);
            if (i == 5) {
                throw new RuntimeException("Boom");
            }
        }
    } /* runtime exception hace rollback de tx A. Se cancela la tx A, pero como no
         realiza ninguna tarea de sql, no hace rollback de nada.
         Cada llamado al método b crea una nueva tx; al llamarse 5 veces previo a la excepción,
         se ejecuta y se comitean satisfactoriamente 5 tx.
         Total tx creadas: 6 → tx A (método a) | rollback
                               tx b (método b, 1er llamado) | commit
                               tx c (método b, 2do llamado) | commit
                               tx d (método b, 3er llamado) | commit
                               tx e (método b, 4to llamado) | commit
                               tx f (método b, 5to llamado) | commit
    */

    public void addTenProductsMandatory() { //método normal, sin tx
        IntStream.rangeClosed(1, 10)
                .mapToObj(i -> "Product " + i)
                .forEach(productRepository::addProductMandatory);
    } /* Este método no crea tx por lo tanto el primer llamado al método b dara exception:
         IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
         Total tx creadas: 0 → Error: método b necesita una tx
    */

    @Transactional
    public void addTenProductsNever() { //crea tx a
        IntStream.rangeClosed(1, 10)
                .mapToObj(i -> "Product " + i)
                .forEach(productRepository::addProductNever);
    } /* commit tx a
        Este método crea tx por lo tanto el primer llamado al método b dara exception:
        IllegalTransactionStateException: Existing transaction found for transaction marked with propagation 'never'
        Total tx creadas: 1 → tx A (método a) → Error: método b debe ejecutarse fuera de una tx
    */


    //https://youtu.be/O9vrhKlGZbE?t=3115
}
