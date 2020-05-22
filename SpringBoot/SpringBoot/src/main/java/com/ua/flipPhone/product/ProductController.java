
package com.ua.flipPhone.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping(path="/product")
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Page<Product>> findAll(Pageable pageable, String searchText) {
        return new ResponseEntity<>(productRepository.findAllPhones(pageable, searchText), HttpStatus.OK);
    }

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
            @RequestParam String product_name){
        Product newProduct = new Product(cpu_gpu, ram_rom, image, screen_size, screen_type, battery, os, selfie_cam,camera,product_name);
        productRepository.save(newProduct);
        
        return "Saved";
    }




}
