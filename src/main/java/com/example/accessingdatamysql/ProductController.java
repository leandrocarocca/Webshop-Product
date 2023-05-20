package com.example.accessingdatamysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    private final ProductRepo repo;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductRepo repo) {
        this.repo = repo;
    }

    @RequestMapping("items")
    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    @RequestMapping("/items/{id}/getItem")
    public Product itemsById(@PathVariable Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            log.info("No item with that id exists");
            return null;
        }
    }
    @PostMapping("items/add")
    public String addProduct(@RequestBody Product p){
        try {
            repo.save(p);
            log.info("Product saved");
            return "Saved";
        } catch(DataAccessException e){
            log.error("Failed to save product", e);
            return "Failed to save product";
        }
    }

    /*@RequestMapping("items/buy")
    public List<Orders> buyProductById(@RequestParam Long customerId, @RequestParam Long productId){
        try{
            this.oRepo.save(new Orders(LocalDateTime.now(),
                    cRepo.findById(customerId).get(),
                    Arrays.asList(this.repo.findById(productId).get())));
            log.info("Order was successfully made!");
            return oRepo.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Something went wrong!");
        }
        return null;
        // ?customerId=1&productId=1
    }*/


}
