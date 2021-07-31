package main;

import config.ProjectConfig;
import domain.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ProductService;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            final Product cookie = new Product();
            cookie.setName("Small Cookie");
            cookie.setPrice(13);
            var productService = context.getBean(ProductService.class);
            //productService.addProduct(cookie); //comportamiento normal
            productService.addProductWithExceptionToSeeTransactionalRollbackFunctionality(cookie); //rollback
            //productService.addProductWorkingAsExpectedToSeeTransactionalCommitFunctionality(cookie); //comportamiento normal en transacción
        }
    }
}
