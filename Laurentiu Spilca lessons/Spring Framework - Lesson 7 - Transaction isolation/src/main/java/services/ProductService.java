package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProductRepository;

/*
Los tipos de aislamientos se utilizan para especificar la forma en que se resolverán las transacciones que son concurrentes.
Tipos de aislamientos:
    A) Default → Si no se especifica, utilizara esta opción, que toma la configuración desde la base de datos.
                 Por lo general, este el valor que usan las bases de datos por defecto suele ser read committed.
    B) READ_COMMITTED
    C) READ_UNCOMMITTED
    D) REPEATABLE_READ
    E) SERIALIZABLE


    Problemas comunes:
     - dirty reads:
        Una lectura sucia sucede cuando el aislamiento es read_uncommitted. Como el nombre lo dice, se pueden leer valores
        que aún no se comitearon. Esto conlleva al problema de que si la transacción "a" que cambio el valor inicial de un dato,
        hace un rollback y simultáneamente la transacción "b", leyó y utilizo el valor ficticio, que posteriormente
        se hizo rollback, la transacción "b" utilizo datos falsos y estropeo los datos, por culpa de la transacción "a".

        En este ejemplo se ve el comportamiento de las transacciones, en el paso del tiempo.
        T1 lee 2 veces el precio del producto mientras que T2 lo modifica.
        El resultado es el precio final del producto luego de ambas transacciones.
        T1 -10---------20---> 20
        T2 -------20------R-> 10         R= rollback

     - repeatable reads
     - phantom reads

     https://youtu.be/QzyucYRGRlk?list=PLEocw3gLFc8WO_HvFzTWUj2fqa7Y8-yg5&t=2693
*/

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public void addTenProduct() {
        for (int i = 1; i <= 10; i++) {
            productRepository.addProduct("Product " + i);
        }
    }
}
