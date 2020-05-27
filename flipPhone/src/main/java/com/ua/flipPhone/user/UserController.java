
package com.ua.flipPhone.user;

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

@Controller
@RequestMapping(path="/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    @GetMapping(path="/types")
    public @ResponseBody List<UserType> getUserTypes(){
        return Arrays.asList(UserType.values());
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
        
        UserType t = null;
        
        
        
        if (type.equals("PROFESSIONAL")){
            t = UserType.PROFESSIONAL;
        }
        else if (type.equals("CLIENT")){
            t = UserType.CLIENT;
        }
        else if (type.equals("SELLER")){
            t = UserType.SELLER;
        }
        else{
            return null;
        }

       
        
        User newUser = new User(password, name, salt, email, address, nif, t);
        userRepository.save(newUser);
        
        return "Saved";
    }
    
    @GetMapping(path="/{user_id}")
    public @ResponseBody Optional<User> getUserById(@PathVariable Integer user_id){       
        return userRepository.findById(user_id);
    }
    
    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteUserById(@RequestParam Integer user_id){
        userRepository.deleteById(user_id);
        return "Deleted";
    }
}
