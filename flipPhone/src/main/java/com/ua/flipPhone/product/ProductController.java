
package com.ua.flipPhone.product;

import com.ua.flipPhone.specifications.SearchCriteria;
import com.ua.flipPhone.specifications.SearchOperation;
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
    
    @GetMapping(path="/filter")
    public @ResponseBody Iterable<Product> getAllProductsByFilter(
            @RequestParam(required=false) String cpu_gpu, 
            @RequestParam(required=false) String ram_rom, 
            @RequestParam(required=false) String image, 
            @RequestParam(required=false) String screen_size, 
            @RequestParam(required=false) String screen_type, 
            @RequestParam(required=false) String battery,
            @RequestParam(required=false) String os,
            @RequestParam(required=false) String selfie_cam,
            @RequestParam(required=false) String camera,
            @RequestParam(required=false) String product_name){
        
        ProductSpecification filter = new ProductSpecification();
        if(cpu_gpu != null){
            filter.add(new SearchCriteria("cpu_gpu",cpu_gpu, SearchOperation.MATCH));
        }
        if(ram_rom != null){
            filter.add(new SearchCriteria("ram_rom",ram_rom, SearchOperation.MATCH));
        }
        if(image != null){
            filter.add(new SearchCriteria("image",image, SearchOperation.MATCH));
        }
        if(screen_size != null){
            filter.add(new SearchCriteria("screen_size",screen_size, SearchOperation.MATCH));
        }
        if(screen_type != null){
            filter.add(new SearchCriteria("screen_type",screen_type, SearchOperation.MATCH));
        }
         if(battery != null){
            filter.add(new SearchCriteria("battery",battery, SearchOperation.MATCH));
        }
        if(os != null){
            filter.add(new SearchCriteria("os",os, SearchOperation.MATCH));
        }
        if(selfie_cam != null){
            filter.add(new SearchCriteria("selfie_cam",selfie_cam, SearchOperation.MATCH));
        }
        if(camera != null){
            filter.add(new SearchCriteria("camera",camera, SearchOperation.MATCH));
        }
        if(product_name != null){
            filter.add(new SearchCriteria("product_name",product_name, SearchOperation.MATCH));
        }
        return productRepository.findAll(filter);
    }
}
