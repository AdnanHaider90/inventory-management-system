package com.ims.dao;

import com.ims.model.Product;
import com.ims.db.DBConnection;

import java.sql.*;
import java.util.*;

public class ProductDAOImpl implements ProductDAO {
    
    @Override
    public void addProduct(Product p){
        String sql = "INSERT INTO products(name, category, quantity, price) VALUES (?,?,?,?)";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
                ps.setString(1, p.getName());
                ps.setString(2, p.getCategory());
                ps.setInt(3, p.getQuantity());
                ps.setDouble(4, p.getPrice());
                ps.executeUpdate();
                System.err.println("✅ Product added sucessfully");
            }
        catch(SQLException e){
            System.out.println("❌ Error adding product " + e.getMessage());
        }
    }

    @Override
    public void updateProduct(Product p){
        String sql = "UPDATE products SET name=?, category=?, quantity=?, price=? where id=?";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){
                ps.setString(1, p.getName());
                ps.setString(2, p.getCategory());
                ps.setInt(3, p.getQuantity());
                ps.setDouble(4, p.getPrice());
                ps.setInt(5, p.getId());
                ps.executeUpdate();
                System.out.println("✅ Product updated successfully");
            }
        catch(SQLException e){
            System.out.println("❌ Error updating Product " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int id){
        String sql = "DELETE FROM products where id=?";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("✅ Product deleted successfully");
        } catch (SQLException e) {
            System.out.println("❌ Error deleting product " + e.getMessage());
        }
    }

    @Override
    public Product getProductById(int id){
        String sql = "SELECT * FROM products WHERE id=?";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                     rs.getDouble("price")
                );
            }
        }
        catch(SQLException e){
            System.out.println("❌ Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> list= new ArrayList<>();
        String sql= "SELECT * FROM products";
        try(Connection con=DBConnection.getConnection();
        Statement st= con.createStatement();
        ResultSet rs= st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                     rs.getDouble("price")
                ));
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error fetching products " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Product> getLowStockProducts(int threshold){
        List<Product> list = new ArrayList<>();
        String sql="SELECT * FROM products WHERE quantity <= ?";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setInt(1, threshold);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"), 
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                ));
            }
            
        } catch (SQLException e) {
            System.out.println("❌ Error " + e.getMessage());
        }
        return list;
    }

}
