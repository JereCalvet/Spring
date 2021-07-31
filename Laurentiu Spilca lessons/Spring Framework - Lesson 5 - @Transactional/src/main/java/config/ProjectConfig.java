package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement //Esta anotación habilita las transacciones
@ComponentScan(basePackages = {"repositories", "services"})
@Configuration
public class ProjectConfig {

    /*Este bean se encarga de la conexión a la base de datos*/
    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/demo");
        dataSource.setUsername("Jere");
        dataSource.setPassword("masterkey");
        return dataSource;
    }

    /*Este bean permite enviar queries a la db sin la necesidad del boiler plate de crear un statement,
    enviarlo a la db, extraer lo resultados, etc.
    Es la implementación de spring de la jdbc api.*/
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /*Este bean se encarga de las transacciones del proyecto*/
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
