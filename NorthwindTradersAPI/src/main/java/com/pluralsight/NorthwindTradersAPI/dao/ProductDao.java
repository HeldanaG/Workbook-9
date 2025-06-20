package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import java.util.List;

public interface ProductDao {
    Product add(Product product);
    List<Product> getAll();

    void delete(int productId);
    void update(int id, Product product);
    Product search(String keyword);
}
