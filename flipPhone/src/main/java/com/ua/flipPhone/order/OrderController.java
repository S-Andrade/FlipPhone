package com.ua.flipPhone.order;

import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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

    
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewOrder(
            @RequestParam  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date date, 
            @RequestParam double total, 
            @RequestParam Integer client_id){
        
        try{
            Optional<User> user = userRepository.findById(client_id);
            Order newOrder = new Order(date,total, user.get());
            orderRepository.save(newOrder);
            return "Saved";
            
        }catch (Exception e){
            return "This user does not exist";
        }
        
    }
    
    @GetMapping(path="/{order_id}")
    public @ResponseBody Optional<Order> getOrderById(@PathVariable Integer order_id){       
        return orderRepository.findById(order_id);
    }
    
}
