package repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate template;

    @Autowired
    public ProductRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void addProduct(String product) {
        String sql = "INSERT INTO product VALUES (NULL, ?, ?)";
        template.update(sql, product, 1);
    }
}
