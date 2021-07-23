package repositories;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
/*    Esta clase permite enviar queries a la db sin la necesidad del boiler plate de crear un statement,
    enviarlo a la db, extraer lo resultados, etc.*/
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Product VALUES (NULL, ?, ?);";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }
}
