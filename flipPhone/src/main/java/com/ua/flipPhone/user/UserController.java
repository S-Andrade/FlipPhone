
package com.ua.flipPhone.user;

import com.ua.flipPhone.admin.Admin;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewAdmin(
            @RequestParam String password, 
            @RequestParam String name, 
            @RequestParam String salt,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String nif,
            @RequestParam String type){
        
        User newUser = new User(password, name, salt, email, address, nif, type);
        userRepository.save(newUser);
        
        return "Saved";
    }
    
    @GetMapping(path="/{user_id}")
    public @ResponseBody Optional<User> getUserById(@PathVariable Integer user_id){       
        return userRepository.findById(user_id);
    }
}
