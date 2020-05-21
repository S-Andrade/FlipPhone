
package com.ua.flipPhone.product;

import org.springframework.data.repository.CrudRepository;

import com.ua.flipPhone.product.Product;


public interface ProductRepository extends CrudRepository<Product, String> {

}