
package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {

    @Autowired
    @Qualifier("jdbcCategoryDao")
    private CategoryDao categoryDao;

    // To list all products
    @GetMapping("/api/categories")
    public List<Category> getAllCategory() {
        return categoryDao.getAll();
    }

    // to allow user to filter for categories
    @GetMapping("/api/categories/search")
    public Category findCategory(@RequestParam String keyword) {
        return categoryDao.search(keyword);
    }

    @PostMapping("/api/categories")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){
        return categoryDao.add(category);
    }

    @PutMapping("/api/categories/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCategory(@PathVariable int id, @RequestBody Category category){
        categoryDao.update(id, category);
    }

    @DeleteMapping("/api/categories/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCategory(@PathVariable int id){
        categoryDao.delete(id);
    }
}
