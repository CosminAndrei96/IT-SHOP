package com.example.demo.Controller;

import com.example.demo.DataBase.Product;
import com.example.demo.Security.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;

    @GetMapping("/admin/products")
    public ModelAndView getProducts(){
        ModelAndView modelAndView = new ModelAndView("/admin/products.html");
        List<Product> productList = productService.findAllProducts();
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @PostMapping("/admin/products")
    @ResponseBody
    public String addProduct(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("cost") Double cost,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("imgSrc") String imgSrc){
    return productService.addProduct(name, category, cost, quantity, imgSrc);
    }
    @DeleteMapping("/admin/products/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable("id")int id) {return productService.deleteProduct(id);}
}

