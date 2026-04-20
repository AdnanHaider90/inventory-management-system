package com.ims.Services;

import com.ims.dao.ProductDAO;
import com.ims.dao.ProductDAOImpl;
import com.ims.model.Product;

import java.util.*;

public class ProductService {
    private ProductDAO dao= new ProductDAOImpl();

    public void addProduct(String name, String category, int quantity, double price){
        dao.addProduct(new Product(name, category, quantity, price));
    }

    public void updateProduct(int id, String name, String category, int quantity, double price){
        Product p=new Product(name, category, quantity, price);
        p.setId(id);
        dao.updateProduct(p);
    }

    public void deleteProduct(int id){
        dao.deleteProduct(id);
    }

    public Product getProductById(int id){
        Product p=dao.getProductById(id);
        if (p==null) {
            System.out.println("[NOT FOUND] No product with id "+id);
        }
        return p;
    }

    public void getAllProducts(){
        List<Product> products=dao.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("[NO PRODUCT FOUND]");
            return;
        }
        System.out.println("+------+------------------+---------------+-----------+-----------+");
        System.out.println("| ID   | Name             | Category      | Quantity  | Price     |");
        System.out.println("+------+------------------+---------------+-----------+-----------+");
        for(Product p : products) System.out.println(p);
        System.out.println("+------+------------------+---------------+-----------+-----------+");
    }

    public void getLowStockProducts(int threshold){
        List<Product> list=dao.getLowStockProducts(threshold);
        if (list.isEmpty()) {
            System.out.println("[OK] All aproducts are sufficiently stocked.");
            return;
        }
        System.out.println("⚠ Low stock alert (<=" + threshold + " units):");
        for(Product p : list) System.out.println(p);
    }
}
