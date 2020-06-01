package com.ua.flipPhone.admin;

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
@RequestMapping(path="/admin")
@CrossOrigin(origins="http://localhost:3000")
public class AdminController {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewAdmin(
            @RequestParam String password, 
            @RequestParam String hash, 
            @RequestParam String email){
        
        Admin newAdmin = new Admin(password, hash, email);
        adminRepository.save(newAdmin);
        
        return "Saved";
    }
    
    @GetMapping(path="/{admin_id}")
    public @ResponseBody Optional<Admin> getAdminById(@PathVariable Integer admin_id){       
        return adminRepository.findById(admin_id);
    }
    
    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteAdminById(@RequestParam Integer admin_id){
        adminRepository.deleteById(admin_id);
        return "Deleted";
    }
    
    @GetMapping(path="/byEmail")
    public @ResponseBody Admin getAdminByEmail(@RequestParam String email){
        return adminRepository.findByEmail(email);
    }
}
