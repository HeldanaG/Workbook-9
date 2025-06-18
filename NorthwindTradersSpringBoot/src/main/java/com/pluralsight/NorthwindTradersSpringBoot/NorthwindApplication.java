package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class NorthwindApplication implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Product Admin Menu ===");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Update Product");
            System.out.println("5. Search Product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Product> products = productDao.getAll();
                    System.out.println("\nProducts:");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case "2":
                    System.out.print("Enter product ID: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter category ID: ");
                    int categoryId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter unit price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    Product newProduct = new Product(id, name, categoryId, price);
                    productDao.add(newProduct);
                    System.out.println("Product added successfully.");
                    break;

                case "3":
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    productDao.delete(deleteId);
                    System.out.println("Product deleted.");
                    break;

                case "4":
                    System.out.print("Enter product ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter new product name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter new category ID: ");
                    int newCategory = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter new unit price: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());

                    Product updatedProduct = new Product(updateId, newName, newCategory, newPrice);
                    productDao.update(updatedProduct);
                    System.out.println("Product updated.");
                    break;

                case "5":
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();

                    List<Product> searchResults = productDao.search(keyword);
                    System.out.println("Search Results:");
                    for (Product p : searchResults) {
                        System.out.println(p);
                    }
                    break;

                case "6":
                    System.out.println("Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
