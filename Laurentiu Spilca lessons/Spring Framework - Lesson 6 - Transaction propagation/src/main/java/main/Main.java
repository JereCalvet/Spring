package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ProductService;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            var service = context.getBean(ProductService.class);
            //service.addTenProductsRequired();
            //service.addTenProductsRequiresNew();
            //service.addTenProductsMandatory();
            service.addTenProductsNever();
        }
    }
}
