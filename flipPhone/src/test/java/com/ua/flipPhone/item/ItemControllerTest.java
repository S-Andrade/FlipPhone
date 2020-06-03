package com.ua.flipPhone.item;

import com.google.common.base.Optional;
import com.ua.flipPhone.admin.Admin;
import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.order.OrderRepository;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.product.ProductRepository;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import com.ua.flipPhone.user.UserType;
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
import java.util.Date;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

    private Item item;

    private Product product;

    private User seller;
    
    private Admin admin;
    
    @MockBean
    ItemSpecification  filter; 

    @BeforeEach
    public void setUp() {
        admin = new Admin(1,"password", "asdfsdfxzv", "maria@email.com");
        product = new Product(1, "Samsung Exynos 9611\n Hz + Quad Core 1.7 GHz",
                "4 GB",
                "300DPI",
                "6,5 \"",
                "sAMOLED Infinity-O FHD+ tátil capacitivo com multi-touch",
                "Li-Ion 4000 mAh",
                "Android 10.0",
                "32.0 MP\n f/2.2",
                "f/2.0 Principal + f/2.2 Ultra Grande Angular + f/2.2 Profundidade + f/2.4 Macro",
                "Smartphone Samsung Galaxy A51 - A515F",
                "url/image",
                admin);

        seller = new User(1, "password", "Maria", "asfwsadgfa", "maria@email.com", "Lisboa", "21423523", UserType.PARTICULAR);

        item = new Item(1, ItemGrade.NEW, "black", 300.50, "v4", product, null, seller);
    }

    @AfterEach
    public void tearDown() {
        item = null;
        product = null;
        seller = null;
        admin= null;
        reset(itemRepository);
        reset(productRepository);
        reset(userRepository);
    }

    @Test
    public void givenItems_whenGetAllItems_thenReturnsJsonArray() throws Exception {

        List<Item> allAdmin = Arrays.asList(item);

        given(itemRepository.findAll()).willReturn(allAdmin);

        mvc.perform(get("/item/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].color", is(item.getColor())));
        verify(itemRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void whenPostItem_thenCreateItem() throws Exception {

        given(productRepository.findById(product.getProduct_id())).willReturn(java.util.Optional.of(product));

        given(userRepository.findById(seller.getUser_id())).willReturn(java.util.Optional.of(seller));

        given(itemRepository.save(Mockito.any(Item.class))).willReturn(item);

        mvc.perform(post("/item/add?grade=NEW&color=black&price=300.5&version=v4&product_id=1&seller_id=1"))
                .andExpect(status().isOk());

        verify(itemRepository, VerificationModeFactory.times(1)).save(Mockito.any(Item.class));
    }

    @Test
    public void givenItem_whenGetItemById_thenReturnJson() throws Exception {
        given(itemRepository.findById(item.getItem_id())).willReturn(java.util.Optional.of(item));

        mvc.perform(get("/item/{item_id}", item.getItem_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("color", is(item.getColor())));
        verify(itemRepository, VerificationModeFactory.times(1)).findById(item.getItem_id());
    }

    @Test
    public void testDeleteById() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/item/delete")
                .param("item_id", String.valueOf(item.getItem_id())))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetItemGrades() throws Exception {
        mvc.perform(get("/item/grades")
                .contentType(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void givenItems_whenGetItemByProductId_thenReturnsJsonArray() throws Exception {

        given(productRepository.findById(product.getProduct_id())).willReturn(java.util.Optional.of(product));

        List<Item> allAdmin = Arrays.asList(item);

        given(itemRepository.findByProductId(item.getProductId())).willReturn(allAdmin);

        mvc.perform(get("/item/byProduct?product_id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].color", is(item.getColor())));
        verify(itemRepository, VerificationModeFactory.times(1)).findByProductId(item.getProductId());
    }

   /* @Test
    public void whenFilter_thenReturnsJsonArray() throws Exception {


        given(productRepository.findById(product.getProduct_id())).willReturn(java.util.Optional.of(product));

        given(userRepository.findById(seller.getUser_id())).willReturn(java.util.Optional.of(seller));

        filter = new ItemSpecification();
        filter.add(new SearchCriteria("grade", ItemGrade.NEW, SearchOperation.EQUAL));
        filter.add(new SearchCriteria("color", "black", SearchOperation.MATCH));
        filter.add(new SearchCriteria("version", "4", SearchOperation.MATCH));
        filter.add(new SearchCriteria("price", 500.0, SearchOperation.LESS_THAN_EQUAL));
        filter.add(new SearchCriteria("productId", product, SearchOperation.EQUAL));
        filter.add(new SearchCriteria("seller_id", seller, SearchOperation.EQUAL));

        
        List<Item> allItem = Arrays.asList(item);
        
                System.out.println(filter);

        
        given(itemRepository.findAll(filter)).willReturn(allItem);
        
        
               
        mvc.perform(get("/item/filter?grade=NEW&color=black&price=<500&version=4&product=1&seller=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].color", is(item.getColor())));
        //verify(itemRepository, VerificationModeFactory.times(1)).findAll(filter);

    }*/
}
