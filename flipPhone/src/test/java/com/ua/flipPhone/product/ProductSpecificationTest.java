
package com.ua.flipPhone.product;

import com.ua.flipPhone.admin.Admin;

import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;



@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProductSpecificationTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ProductRepository productRepository;
    
   
    
    @Test
    public void testMatch() {
        Admin admin = new Admin("pass","sdifg","toze@mail.com");
        entityManager.persistAndFlush(admin);
        Product product1 = new Product("Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
                                        "4 GB", 
                                        "300DPI", 
                                        "6,5 \"", 
                                        "sAMOLED Infinity-O FHD+ tátil capacitivo com multi-touch", 
                                        "Li-Ion 4000 mAh", 
                                        "Android 10.0", 
                                        "32.0 MP\n f/2.2",
                                        "f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro",
                                        "Smartphone Samsung Galaxy A51 - A515F",
                                        "url/image", admin);
        entityManager.persistAndFlush(product1);
        Product product2 = new Product("Qualcomm Snapdragon 865", 
                                        "8 GB", 
                                        "125DPI", 
                                        "6,55 \"", 
                                        "Fluid AMOLED tátil capacitivo com multi-touch\\n Vidro 3D Corning Gorilla Glass", 
                                        "Li-Po 4300 mAh\\n Fast battery charging 5V/6A", 
                                        "Android 10.0\\nOxygenOS", 
                                        "16.0 MP\\n Sensor: Sony MX471, 1.0um, EIS, f/2.0",
                                        "48.0 MP + 16.0 MP + 2.0 MP",
                                        "Smartphone OnePlus 8",
                                        "url/image", admin);
        entityManager.persistAndFlush(product2);
        
        
        ProductSpecification spec = new ProductSpecification();
        spec.add(new SearchCriteria("product_name", "Samsung", SearchOperation.MATCH));
        
        List<Product> expected = new ArrayList<Product>();
        expected.add(product1);
        
        List<Product> result = productRepository.findAll(spec);
        
        assertThat(expected.equals(result));
        
    }
    
    
}
