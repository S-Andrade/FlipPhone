package com.ua.flipPhone.item;


import com.ua.flipPhone.product.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
   
    Iterable<Item> findByProductId(Product productId);

}
