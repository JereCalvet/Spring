package repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
Si marco la clase con esta anotación, todos los métodos de la clase se convierten en transaccionales con las propiedades
aquí declaradas. Siempre puedo sobreescribir este comportamiento anotando un método en específico con @Transactional.
@Transactional
* */
@Repository
public class ProductRepository {
    private final JdbcTemplate template;

    @Autowired
    public ProductRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addProductRequired(String product) { //verifica si hay tx, como existe tx A la utiliza
        String sql = "INSERT INTO product VALUES (NULL, ?, ?)";
        template.update(sql, product, 1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addProductRequiresNew(String product) { //create tx B
        String sql = "INSERT INTO product VALUES (NULL, ?, ?)";
        template.update(sql, product, 1);
    } // commit tx B

    @Transactional(propagation = Propagation.MANDATORY)
    public void addProductMandatory(String product) { //Necesita tx, si no existe fallará
        String sql = "INSERT INTO product VALUES (NULL, ?, ?)";
        template.update(sql, product, 1);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void addProductNever(String product) { //Necesita que no exista una tx, si existe fallara
        String sql = "INSERT INTO product VALUES (NULL, ?, ?)";
        template.update(sql, product, 1);
    }
}
