package com.example.demo.Security;

import com.example.demo.DataBase.Product;
import com.example.demo.DataBase.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO; //injected

    public List<Product> findAllProducts(){
        return (List<Product>) productDAO.findAll();

    }

    public String addProduct(String name, String category, Double cost, Integer quantity, String imgSrc){
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setCost(cost);
        product.setQuantity(quantity);
        product.setImgSrc(imgSrc);
        productDAO.save(product);
        return "The product " + name + "was added.";
    }

    public String deleteProduct(int id){
        productDAO.deleteById(id);
        return "The product with id = " +id + " was deleted.";
    }

    public Product getProductDetailsById(int id){
        return productDAO.findById(id).get();
    }


    public List<Product> getProductDetailsByCategory(String category){
        return productDAO.findAllByCategory(category);
    }
}
