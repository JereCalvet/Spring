package main;

import config.ProjectConfig;
import domain.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.ProductRepository;

public class Main {
    public static void main(String[] args) {
        try (final var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {

            /*Product beer = new Product();
            beer.setName("Beer");
            beer.setPrice(10);*/

            final ProductRepository productRepo = context.getBean(ProductRepository.class);

            //productRepo.addProduct(beer);

            productRepo.getProducts().forEach(System.out::println);

            //https://youtu.be/_t3BbWs5PcI?list=PLEocw3gLFc8WO_HvFzTWUj2fqa7Y8-yg5&t=1737
        }
    }
}
