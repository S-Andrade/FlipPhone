package com.ua.flipPhone.item;


import org.springframework.data.repository.CrudRepository;

import com.ua.flipPhone.item.Item;
import com.ua.flipPhone.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ItemRepository extends CrudRepository<Item, Integer> {
   
    Iterable<Item> findByProductId(Product productId);
    /*@Modifying
    @Query(value = "select ",
        nativeQuery = true)
    List<Item> filter(@Param("color") String color);*/

}
