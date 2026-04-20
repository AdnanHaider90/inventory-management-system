package com.ims.dao;
import com.ims.model.Product;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    Product getProductById(int id);
    List<Product> getAllProducts();
    List<Product> getLowStockProducts(int threshold);
}
