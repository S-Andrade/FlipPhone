
package com.ua.flipPhone.product;

import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class ProductSpecificationTest {
    
    @MockBean
    private ProductRepository productRepository;
    
    private Product product1;
    
    private Product product2;
    
    
    @BeforeEach
    public void setUp(){
        Admin admin = new Admin("pass","sdifg","toze@mail.com");
        product1 = new Product("Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz", 
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
        
        product2 = new Product("Qualcomm Snapdragon 865", 
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
        
    }
    
    @AfterEach
    public void tearDown() {
        product1 = null;
        product2 = null;
        reset(productRepository);
    }
    
   
    
    @Test
    public void testMatch() {
        ProductSpecification spec = new ProductSpecification();
        spec.add(new SearchCriteria("product_name", "Samsung", SearchOperation.MATCH));
        
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(product1);
        given(productRepository.findAll(spec)).willReturn(listProduct);
    }
}
