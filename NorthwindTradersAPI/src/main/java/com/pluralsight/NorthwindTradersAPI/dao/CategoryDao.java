package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import java.util.List;

public interface CategoryDao {
    Category add(Category category);
    List<Category> getAll();

    void delete(int CategoryID);
    void update(int id, Category category);
    Category search(String keyword);
}