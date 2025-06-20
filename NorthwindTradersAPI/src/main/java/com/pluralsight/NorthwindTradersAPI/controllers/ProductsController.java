package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    @Qualifier("jdbcProductDao")
    private ProductDao productDao;

    // To list all products
    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    // to allow user to filter for product
    @GetMapping("/api/products/search")
    public Product findProduct(@RequestParam String keyword) {
        return productDao.search(keyword);
    }

    @PostMapping("/api/products")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        return productDao.add(product);
    }

    @PutMapping("/api/products/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateProduct(@PathVariable int id, @RequestBody Product product){
        productDao.update(id, product);
    }

    @DeleteMapping("/api/products/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProduct(@PathVariable int id){
        productDao.delete(id);
    }
}
