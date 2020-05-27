package com.ua.flipPhone.payment;

 import com.ua.flipPhone.order.Order;
import com.ua.flipPhone.order.OrderRepository;
import com.ua.flipPhone.user.User;
import com.ua.flipPhone.user.UserRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
@RequestMapping(path="/payment")
public class PaymentController {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;

    
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }
    
    @GetMapping(path="/status")
    public @ResponseBody List<PaymentStatus> getPaymentStatus(){
        return Arrays.asList(PaymentStatus.values());
    }
    
    @GetMapping(path="/gateway")
    public @ResponseBody List<PaymentGateway> getPaymentGateways(){
        return Arrays.asList(PaymentGateway.values());
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewPayment(
            @RequestParam String status,
            @RequestParam String gateway,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") Date date,
            @RequestParam Integer order_id,
            @RequestParam Integer client_id,
            @RequestParam Integer seller_id){
        
        Order order;
        User client;
        User seller;
        
        PaymentStatus s = null;
        PaymentGateway g = null;
        
                
        try{
            Optional<Order> op_order = orderRepository.findById(order_id);
            order = op_order.get();
        }catch(Exception e){
           return null; 
        }
        
        try{
            Optional<User> op_client = userRepository.findById(client_id);
            client = op_client.get();
        }catch(Exception e){
            return null;
        }
        
        try{
            Optional<User> op_seller = userRepository.findById(seller_id);
            seller = op_seller.get();
        }catch(Exception e){
            return null;
        }
        
        
        if (status.equals("PENDING")){
            s = PaymentStatus.PENDING;
        }
        else if (status.equals("RECEIVED")){
            s = PaymentStatus.RECEIVED;
        }
        else if (status.equals("SENT")){
            s = PaymentStatus.SENT;
        }
        else{
            return null;
        }
        
        
        if (gateway.equals("CREDIT_CARD")){
            g = PaymentGateway.CREDIT_CARD;
        }
        else if (gateway.equals("DEBIT_CARD")){
            g = PaymentGateway.DEBIT_CARD;
        }
        else if (gateway.equals("MBWAY")){
            g = PaymentGateway.MBWAY;
        }
        else if (gateway.equals("PAYPAL")){
            g = PaymentGateway.PAYPAL;
        }
        else{
            return null;
        }
        
        
        Payment newPayment = new Payment(s, g, date, order, client, seller);
        paymentRepository.save(newPayment);
        return "Saved";
        
    }
    
    @GetMapping(path="/{payment_id}")
    public @ResponseBody Optional<Payment> getPaymentById(@PathVariable Integer payment_id){       
        return paymentRepository.findById(payment_id);
    }
    
    @DeleteMapping(path="/delete")
    public @ResponseBody String deletePaymentById(@RequestParam Integer payment_id){
        paymentRepository.deleteById(payment_id);
        return "Deleted";
    }
    
}
