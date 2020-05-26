
package com.ua.flipPhone.product;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product")
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewProduct(
            @RequestParam String cpu_gpu, 
            @RequestParam String ram_rom, 
            @RequestParam String image, 
            @RequestParam String screen_size, 
            @RequestParam String screen_type, 
            @RequestParam String battery,
            @RequestParam String os,
            @RequestParam String selfie_cam,
            @RequestParam String camera,
            @RequestParam String product_name,
            @RequestParam String photoUrl){
        Product newProduct = new Product(cpu_gpu, ram_rom, image, screen_size, screen_type, battery, os, selfie_cam,camera,product_name,photoUrl);
        productRepository.save(newProduct);
        
        return "Saved";
    }

    @GetMapping(path="/{product_id}")
    public @ResponseBody Optional<Product> getAdminById(@PathVariable Integer product_id){       
        return productRepository.findById(product_id);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteProductById(@RequestParam Integer product_id){
        productRepository.deleteById(product_id);
        return "Deleted";
    }
}
