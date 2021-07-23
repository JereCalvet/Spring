package repositories;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

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
        String sql = "INSERT INTO Product VALUES (NULL, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    public List<Product> getProducts() {
        String sql = "SELECT * FROM Product";

        /*Ejecución de la query y mapeo con clase anonima*/
        /*final List<Product> selectResult = jdbcTemplate.query(sql, new RowMapper<Product>() {

            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                return mapProduct(rs);
            }
        });*/

        /*Ejecución de la query y mapeo con lambda*/
        final List<Product> selectResult = jdbcTemplate.query(sql, (rs, i) -> mapProduct(rs));
        return selectResult;
    }

    private Product mapProduct(ResultSet rs) throws SQLException {
        final Product result = new Product();
        result.setId(rs.getInt("idProduct"));
        result.setName(rs.getString("name"));
        result.setPrice(rs.getDouble("price"));
        return result;
    }
}
