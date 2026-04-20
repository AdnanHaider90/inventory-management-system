package com.ims.main;

import java.util.Scanner;

import com.ims.Services.ProductService;
import com.ims.model.Product;

public class Main {
    public static void main(String[] args) {
        ProductService service=new ProductService();
        Scanner sc=new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Inventory Management System =====");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View All Product");
            System.out.println("5. View Product By Id");
            System.out.println("6. Low Stock Alert");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");      String name =sc.nextLine();
                    System.out.print("Category: ");  String cat =sc.nextLine();
                    System.out.print("Quantity: ");  int qty =sc.nextInt();
                    System.out.print("Price: ");     double price =sc.nextDouble();
                    service.addProduct(name, cat, qty, price);
                    break;

                case 2:
                    System.out.print("Product id to update: "); int uid=sc.nextInt(); sc.nextLine();
                    System.out.print("Name: ");      String uname =sc.nextLine();
                    System.out.print("Category: ");  String ucat =sc.nextLine();
                    System.out.print("Quantity: ");  int uqty =sc.nextInt();
                    System.out.print("Price: ");     double uprice =sc.nextDouble();
                    service.updateProduct(uid, uname, ucat, uqty, uprice);
                    break;

                case 3:
                    System.out.print("Product id to delete: ");
                    service.deleteProduct(sc.nextInt());
                    break;

                case 4:
                    service.getAllProducts();
                    break;

                case 5:
                    System.out.print("Enter id to view Current Product: "); int cid=sc.nextInt();sc.nextLine();
                    Product existing=service.getProductById(cid);
                    if(existing==null) break;
                    System.out.println(existing);
                    break;
                    
                case 6:
                    System.out.print("Enter threshold quantity: ");
                    service.getLowStockProducts(sc.nextInt());
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
}
