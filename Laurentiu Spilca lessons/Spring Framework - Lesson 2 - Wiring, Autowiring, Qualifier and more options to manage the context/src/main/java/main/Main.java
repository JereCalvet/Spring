package main;

import beans.Cat;
import beans.Owner;
import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            final Cat cat = context.getBean(Cat.class);
            final Owner owner = context.getBean(Owner.class);

            System.out.println(cat);
            System.out.println(owner);


            //https://youtu.be/uXkXMSnRWkU?t=1614
        }
    }
}
