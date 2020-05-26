package com.ua.flipPhone.order;

import com.ua.flipPhone.item.Item;
import com.ua.flipPhone.item.ItemRepository;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/order")
public class OrderController {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewOrder(
            @RequestParam  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date date, 
            @RequestParam double total, 
            @RequestParam Integer client_id,
            @RequestParam Integer item_id){
        
        User client;
        Item item;
        
        
        try{
            Optional<User> user = userRepository.findById(client_id);
            client = user.get();            
        }catch (Exception e){
            return "This user does not exist";
        }
        
        try{
            Optional<Item> op_item = itemRepository.findById(item_id);
            item=op_item.get();
        }catch (Exception e){
            return "This item does not exist";
        }
        
                
        Order newOrder = new Order(date,total,client);
        if(item.getOrder_id() == null){
            item.setOrder_id(newOrder);
            itemRepository.save(item);
            orderRepository.save(newOrder);
            return "Saved";
        }else{
            return "This item is already assigned to anather order";
        }
              
    }
    
    @GetMapping(path="/{order_id}")
    public @ResponseBody Optional<Order> getOrderById(@PathVariable Integer order_id){       
        return orderRepository.findById(order_id);
    }
    
    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteOrderById(@RequestParam Integer order_id){
        orderRepository.deleteById(order_id);
        return "Deleted";
    }
}
