package com.ua.flipPhone.item;

import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.order.OrderRepository;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.product.ProductRepository;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/item")
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewItem(
            @RequestParam String grade, 
            @RequestParam String color, 
            @RequestParam Double price,
            @RequestParam String version,
            @RequestParam Integer product_id,
            @RequestParam Integer seller_id){
        
        Product product;
        User seller;
        
        try{
            Optional<Product> op_product = productRepository.findById(product_id);
            product = op_product.get();
        }catch(Exception e){
            return "This product does not exist";
        }
        
        
        try{
            Optional<User> op_user = userRepository.findById(seller_id);
            seller = op_user.get();
        }catch(Exception e){
            return "This user does not exist";
        }
        
        Item newItem = new Item(grade, color, price, version, product,null, seller);
        itemRepository.save(newItem);
        return "Saved";
    }
    
    @GetMapping(path="/{item_id}")
    public @ResponseBody Optional<Item> getItemById(@PathVariable Integer item_id){       
        return itemRepository.findById(item_id);
    }
    
    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteItemById(@RequestParam Integer item_id){
        itemRepository.deleteById(item_id);
        return "Deleted";
    }
    
    @GetMapping(path="/byProduct")
    public @ResponseBody Iterable<Item> getItemByProductId(@RequestParam Integer product_id) {
        Product product;
      
       /// try{
            Optional<Product> op_product = productRepository.findById(product_id);
            product = op_product.get();
        //}catch(Exception e){
            //throw EntityNotFoundExceptio("This product does not exist");
       // }
        return itemRepository.findByProductId(product);
    }
    
    
    @GetMapping(path="/filter")
    public @ResponseBody String getAllItemsByFilter(
            @RequestParam(required=false) String grade,
            @RequestParam(required=false) String color,
            @RequestParam(required=false) String price,
            @RequestParam(required=false) String version,
            @RequestParam(required=false) String product,
            @RequestParam(required=false) String seller){
        
        
        
        return "";
    }

    private Exception EntityNotFoundExceptio(String this_product_does_not_exist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  }
