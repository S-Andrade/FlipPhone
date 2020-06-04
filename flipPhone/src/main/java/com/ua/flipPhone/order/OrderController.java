package com.ua.flipPhone.order;

import com.ua.flipPhone.item.Item;
import com.ua.flipPhone.item.ItemRepository;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/order")
@CrossOrigin(origins="http://192.168.160.49:3000")
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
            @RequestParam  String date, 
            @RequestParam double total, 
            @RequestParam Integer client_id,
            @RequestParam String item_id){
        
        User client;
        List<Item> itemList = new ArrayList<>();
        
        
        
        try{
            Optional<User> user = userRepository.findById(client_id);
            client = user.get();            
        }catch (Exception e){
            return null;
        }
        
        String[] list_item = item_id.split(",");
        for(String i: list_item){
            try{
                Optional<Item> op_item = itemRepository.findById(Integer.parseInt(i));
                itemList.add(op_item.get());
            }catch (Exception e){
                return null;
            }
        }
        
        
        Order newOrder = new Order(date,total,client);
        orderRepository.save(newOrder);
        for(Item i : itemList){
            if(i.getOrder_id() == null){
                i.setOrder_id(newOrder);
                itemRepository.save(i);
            }
        }
        return "Saved";
                   
    }
    
    @PostMapping(path="/addItem")
    public @ResponseBody String addItem(@RequestParam Integer item_id, @RequestParam Integer order_id){
        Item item;
        Order order;
        
        try{
            Optional<Order> op_order = orderRepository.findById(order_id);
            order = op_order.get();
        }catch (Exception e){
            return null;
        }
        
        try{
            Optional<Item> op_item = itemRepository.findById(item_id);
            item = op_item.get();
        }catch (Exception e){
            return null;
        }
        
        item.setOrder_id(order);
        itemRepository.save(item);
        return "Saved";
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
