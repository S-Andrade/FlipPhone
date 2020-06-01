
package com.ua.flipPhone.product;

import com.google.common.base.Optional;
import com.ua.flipPhone.item.ItemSpecification;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import org.mockito.Mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;
    
    private Product product;
    
    @BeforeEach
    public void setUp(){
        product = new Product(1,
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
                "url/image");
    }
    
    @AfterEach
    public void tearDown() {
        product = null;
        reset(productRepository);

    }
    
    
    @Test
    public void givenProducts_whenGetAllProducts_thenReturnsJsonArray() throws Exception {
                
        List<Product> allproduct = Arrays.asList(product);
        
        given(productRepository.findAll()).willReturn(allproduct);
        
        mvc.perform(get("/product/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].product_name", is(product.getProduct_name())));
        verify(productRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void whenPostProduct_thenCreateProduct() throws Exception {
        given(productRepository.save(Mockito.any())).willReturn(product);
        
        mvc.perform(post("/product/add")
                .param("cpu_gpu", "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz")
                .param("ram_rom","4 GB")
                .param("image","300DPI")
                .param("screen_size","6,5 \"")
                .param("screen_type","sAMOLED Infinity-O FHD+ tátil capacitivo com multi-touch")
                .param("battery","Li-Ion 4000 mAh")
                .param("os","Android 10.0")
                .param("selfie_cam","32.0 MP\n f/2.2")
                .param("camera","f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro")
                .param("product_name","Smartphone Samsung Galaxy A51 - A515F")
                .param("photoUrl","url/image"))
                .andExpect(status().isOk());

        verify(productRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    }
    
    @Test
    public void givenProduct_whenGetProductById_thenReturnJson() throws Exception {
        
        given(productRepository.findById(product.getProduct_id())).willReturn(java.util.Optional.of(product));
        
        mvc.perform(get("/product/{product_id}",product.getProduct_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("product_name", is(product.getProduct_name())));
        verify(productRepository, VerificationModeFactory.times(1)).findById(product.getProduct_id());
    }
    
    @Test
    public void testDeleteById() throws Exception {
              
        mvc.perform(MockMvcRequestBuilders.delete("/product/delete")
                .param("product_id", String.valueOf(product.getProduct_id())))
                .andExpect(status().isOk());
    }
    
   /* @Test
    public void whenFilter_thenReturnsJsonArray() throws Exception {
       

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
        given(productRepository.findAll(filter)).willReturn(allProduct);

        mvc.perform(get("/product/filter?cpu_gpu=Quad Core&ram_rom=4&image=300&screen_size=6,5&screen_type=multi-touch&battery=4000&os=Android&selfie_cam=32.0&camera=f/2.0&product_name=Samsung")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].product_name", is(product.getProduct_name())));
        verify(productRepository, VerificationModeFactory.times(1)).findAll(filter);

    }*/
    
}
