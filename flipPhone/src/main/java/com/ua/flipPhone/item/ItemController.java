package com.ua.flipPhone.item;

import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.order.OrderRepository;
import com.ua.flipPhone.product.Product;
import com.ua.flipPhone.product.ProductRepository;
import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequestMapping(path="/item")
@CrossOrigin(origins="http://localhost:3000")
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }
    
    @GetMapping(path="/grades")
    public @ResponseBody List<ItemGrade> getItemGrades(){
        return Arrays.asList(ItemGrade.values());
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
        ItemGrade g = null;
        
        try{
            Optional<Product> op_product = productRepository.findById(product_id);
            product = op_product.get();
            System.out.println(product);
        }catch(Exception e){
            return null;
        }
        
        
        try{
            Optional<User> op_user = userRepository.findById(seller_id);
            seller = op_user.get();
        }catch(Exception e){
            return null;
        }
        
        
        if (grade.equals("NEW")){
            g = ItemGrade.NEW;
        }
        else if (grade.equals("LIKE_NEW")){
            g = ItemGrade.LIKE_NEW;
        }
        else if (grade.equals("GOOD_STATE")){
            g = ItemGrade.GOOD_STATE;
        }
        else if (grade.equals("FUNCTIONAL")){
            g = ItemGrade.FUNCTIONAL;
        }
        else{
            return null;
        }
            
        
        
        
        
        Item newItem = new Item(g, color, price, version, product, null, seller);
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
      
        try{
            Optional<Product> op_product = productRepository.findById(product_id);
            product = op_product.get();
        }catch(Exception e){
            return null;
        }
        return itemRepository.findByProductId(product);
    }
    
    @GetMapping(path="/filter")
    public @ResponseBody Iterable<Item> getAllItemsByFilter(
            @RequestParam(required=false) String grade,
            @RequestParam(required=false) String color,
            @RequestParam(required=false) String price,
            @RequestParam(required=false) String version,
            @RequestParam(required=false) Integer product,
            @RequestParam(required=false) Integer seller){
        
        ItemSpecification filter = new ItemSpecification();
        
        if(grade != null){
            ItemGrade g = null;
            if (grade.equals("NEW")){
                g = ItemGrade.NEW;
            }
            else if (grade.equals("LIKE_NEW")){
                g = ItemGrade.LIKE_NEW;
            }
            else if (grade.equals("GOOD_STATE")){
                g = ItemGrade.GOOD_STATE;
            }
            else if (grade.equals("FUNCTIONAL")){
                g = ItemGrade.FUNCTIONAL;
            }
            else{
                return null;
            }
            filter.add(new SearchCriteria("grade",g, SearchOperation.EQUAL));
        }
        if(color != null){
            filter.add(new SearchCriteria("color",color, SearchOperation.MATCH));
        }
        if(version != null){
            filter.add(new SearchCriteria("version",version, SearchOperation.MATCH));
        }
        if(price != null){
            String op = price.substring(0, 1);
            String n = price.substring(1);
            Double number = Double.parseDouble(n);
            if(op.equals("<")){
                filter.add(new SearchCriteria("price", number, SearchOperation.LESS_THAN_EQUAL));
            }
            if(op.equals(">")){
               filter.add(new SearchCriteria("price", number, SearchOperation.GREATER_THAN_EQUAL)); 
            }
        }
        
        if(product != null){
            Product pro;
      
            try{
                Optional<Product> op_product = productRepository.findById(product);
                pro = op_product.get();
            }catch(Exception e){
                return null;
            }
            filter.add(new SearchCriteria("productId",pro, SearchOperation.EQUAL));
        }
        if(seller != null){
            User user;
      
            try{
                Optional<User> op_user = userRepository.findById(seller);
                user = op_user.get();
            }catch(Exception e){
                return null;
            }
            filter.add(new SearchCriteria("seller_id",seller, SearchOperation.EQUAL));
        }
        
         
        return itemRepository.findAll(filter);
    }

    
  }
