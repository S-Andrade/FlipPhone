
package com.ua.flipPhone.product;

import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    
    @Autowired
    private ProductRepository productRepository;
       
  
    @Test
    public void testSave(){
        Admin admin = new Admin("chave", "askfyg", "ze@mail.com");
        entityManager.persistAndFlush(admin);
        Product product = new Product(
                "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
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
        
       productRepository.save(product);
       
       Optional<Product> result = productRepository.findById(product.getProduct_id());
        
       assertThat(Optional.of(product).equals(result));
    }
    
    @Test
    public void testFindById(){
        Admin admin = new Admin("chave", "askfyg", "ze@mail.com");
        entityManager.persistAndFlush(admin);
        Product product = new Product(
                "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
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
        entityManager.persistAndFlush(product);
        
        Optional<Product> result = productRepository.findById(product.getProduct_id());
        
        assertThat(Optional.of(product).equals(result));
    }
    
    @Test
    public void testFindAll(){
        Admin admin = new Admin("chave", "askfyg", "ze@mail.com");
        entityManager.persistAndFlush(admin);
        Product product = new Product(
                "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
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
        entityManager.persistAndFlush(product);
        
        List<Product> expected = new ArrayList<Product>();
        expected.add(product);
        
        List<Product> result = productRepository.findAll();
        
        assertThat(expected.equals(result));
    }
    
    @Test
    public void testDeleteById(){
        Admin admin = new Admin("chave", "askfyg", "ze@mail.com");
        entityManager.persistAndFlush(admin);
        Product product = new Product(
                "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
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
        entityManager.persistAndFlush(product);
        
        productRepository.deleteById(product.getProduct_id());
        
        Optional<Product> result = productRepository.findById(product.getProduct_id());
        
        assertEquals(Optional.empty(), result);

    }
    
    @Test
    public void testFilter() {
        
        Admin admin = new Admin("chave", "askfyg", "ze@mail.com");
        entityManager.persistAndFlush(admin);
        Product product = new Product(
                "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
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
        entityManager.persistAndFlush(product);
        
        ProductSpecification filter = new ProductSpecification();
        filter.add(new SearchCriteria("cpu_gpu", "Quad Core", SearchOperation.MATCH));
        filter.add(new SearchCriteria("ram_rom", "4", SearchOperation.MATCH));
        filter.add(new SearchCriteria("image", "300", SearchOperation.MATCH));
        filter.add(new SearchCriteria("screen_size", "6,5", SearchOperation.MATCH));
        filter.add(new SearchCriteria("screen_type", "multi-touch", SearchOperation.MATCH));
        filter.add(new SearchCriteria("battery", "4000", SearchOperation.MATCH));
        filter.add(new SearchCriteria("os", "Android", SearchOperation.MATCH));
        filter.add(new SearchCriteria("selfie_cam", "32.0", SearchOperation.MATCH));
        filter.add(new SearchCriteria("camera", "f/2.0", SearchOperation.MATCH));
        filter.add(new SearchCriteria("product_name", "Samsung", SearchOperation.MATCH));
        
        List<Product> expected = new ArrayList<Product>();
        expected.add(product);
        
        List<Product> result = productRepository.findAll(filter);
        
        assertThat(expected.equals(result));
        
    }
}
