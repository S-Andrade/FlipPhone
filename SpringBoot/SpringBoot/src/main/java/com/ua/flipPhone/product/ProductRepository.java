
package com.ua.flipPhone.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import com.ua.flipPhone.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends CrudRepository<Product, String> {
    @Query("From Product p WHERE p.product_name=:searchText OR p.image=:searchText")
    Page<Product> findAllPhones(Pageable pageable, @Param("searchText") String searchText);

};
