package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcProductDao implements ProductDao {

    @Autowired
    private DataSource dataSource;



//    public JdbcProductDao(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Override
    public Product add(Product product) {

        String sql = "INSERT INTO products (ProductID, ProductName, CategoryID, UnitPrice) VALUES (?, ?, ?,?)";


        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product.getProductId());

            stmt.setString(2, product.getName());

            stmt.setInt(3, product.getCategoryId());

            stmt.setDouble(4, product.getPrice());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("ProductName"));
                product.setCategoryId(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("UnitPrice"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    @Override
    public void delete(int productId) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Product product) {
        String sql = "UPDATE Products SET ProductName = ?, CategoryID = ?, UnitPrice = ? WHERE ProductID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getProductId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product search(String keyword) {

        Product product = new Product();

        String sql = "SELECT * FROM Products " +
                "WHERE CAST(ProductID AS CHAR) LIKE ? " +
                "OR ProductName LIKE ? " +
                "OR CAST(CategoryID AS CHAR) LIKE ? " +
                "OR CAST(UnitPrice AS CHAR) LIKE ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String like = "%" + keyword + "%";
            stmt.setString(1, like);
            stmt.setString(2, like);
            stmt.setString(3, like);
            stmt.setString(4, like);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                product.setProductId(rs.getInt("ProductID"));
                product.setName(rs.getString("ProductName"));
                product.setCategoryId(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("UnitPrice"));
                return product;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
