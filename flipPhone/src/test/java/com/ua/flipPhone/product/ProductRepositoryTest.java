
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {
    
    @Mock
    private ProductRepository productRepository;
       
    private Product product;
    
    private Admin admin;
    
    
    @BeforeEach
    public void setUp(){
        admin = new Admin("chave", "askfyg", "ze@mail.com");
         product = new Product(
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
    }
    
    @AfterEach
    public void tearDown() {
        product = null;
        admin = null;
        reset(productRepository);
    }
    
    @Test
    public void testSave(){
        when(productRepository.save(product)).thenReturn(product);
    }
    
    @Test
    public void testFindById(){
        when(productRepository.findById(product.getProduct_id())).thenReturn(Optional.of(product));
    }
    
    @Test
    public void testFindAll(){
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(product);
        when(productRepository.findAll()).thenReturn(listProduct);
    }
    
    @Test
    public void testDeleteById(){
        productRepository.deleteById(product.getProduct_id());
        when(productRepository.findById(product.getProduct_id())).thenReturn(null);
    }
    
    @Test
    public void testFilter() {
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
        
        List<Product> allProduct = Arrays.asList(product);
        when(productRepository.findAll(filter)).thenReturn(allProduct);

    }
}
