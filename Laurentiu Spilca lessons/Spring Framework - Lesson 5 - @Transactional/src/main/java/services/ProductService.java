package services;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    /*
     Esta anotación convierte el método en transaccional. Es decir, le da atomicidad al método. Se ejecuta
     por completo sin errores o no se ejecuta nada.
     La forma en que funciona es a través de un aspecto que toma todos los métodos anotados con
     @Transactional y le agrega la funcionalidad para lograr la atomicidad. En otras palabras, el point cut
     del aspecto (el/los método interceptados) serían los métodos anotado con @Transactional a los cuales
     se les agregará la funcionalidad de la atomicidad.
     Estas operaciones estarán a cargo del platform transaction manager.

     Las transacciones tienen varias reglas:
        a) El comportamiento por defecto de las transacciones establece que se van a cancelar solamente cuando sucede una
        runtime exception. Con checked exceptions no se realiza el rollback y los datos se persistirán.
        Este comportamiento se puede customizar agregando el atributo a la anotación así @Transactional(rollbackFor = Exception.class).
        Ademas hay otros atributos que permiten continuar con las customizaciones: @Transactional(noRollbackFor = RuntimeException.class)

        Un detalle importante a considerar de esta regla es que, si la excepción que cancela la transacción, es tratada dentro
        del método (try-catch), la transacción no se detiene y se commitea. Esto se debe a que el aspecto que rodea la transacción
        nunca ve la excepción sucediendo. En otras palabras, las transacciones se cancelaran, si y solo si, la excepción se propaga fuera del método.
     */
    @Transactional
    public void addProductWithExceptionToSeeTransactionalRollbackFunctionality(Product product) {
        addProduct(product);
        System.out.println("Log: producto agregado.\n--A continuación tiro excepción--");
        throw new RuntimeException("Excepción para chequear el rollback de la transacción.");
    }

    @Transactional
    public void addProductWorkingAsExpectedToSeeTransactionalCommitFunctionality(Product product) {
        addProduct(product);
        System.out.println("Log: producto agregado.\n--A continuación se debería commitear sin problemas--");
    }
}
