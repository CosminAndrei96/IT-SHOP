package com.example.demo.Controller;

import com.example.demo.DataBase.*;
import com.example.demo.Security.ProductService;
import com.example.demo.Security.UserService;
import com.example.demo.Security.UserSession;
import com.example.demo.Service.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    UserSession userSession;

    @Autowired
    OrderLinesDAO orderLinesDAO;

    @Autowired
    OrderService orderService;

    int items=0;
    @GetMapping("/register-form")
    public ModelAndView registerAction(@RequestParam("fullName") String fullName,
                                       @RequestParam("userName") String userName,
                                       @RequestParam("phoneNumber") String phoneNumber,
                                       @RequestParam("email") String email,
                                       @RequestParam("password1") String password1,
                                       @RequestParam("password2") String password2,
                                       @RequestParam("adress") String adress,
                                       @RequestParam("gender") String gender

                                        ){
        ModelAndView modelAndView = new ModelAndView("register");
            try {
                userService.registerUser(fullName,userName,phoneNumber,email,password1,password2,adress,gender);
            }catch (UserException e){
                modelAndView.addObject("message", e.getMessage());
                return modelAndView;
            }
            return new ModelAndView("redirect:index.html");
    }
    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password1") String password
                             ){
        ModelAndView modelAndView = new ModelAndView("index");
        List<User> userList;
        try{
            userList = userService.loginUser(email,password);
        }catch (UserException e){
            modelAndView.addObject("message",e.getMessage());
            return modelAndView;
        }
        userSession.setId(userList.get(0).getId());
        return new ModelAndView("redirect:dashboard");
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(){
        ModelAndView modelAndView = new ModelAndView("index");
        if (userSession.getId() <= 0) {
            return modelAndView;
        }
        List<Product> productList = productService.findAllProducts();
        modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("productList",productList);
        items = userSession.getCartSize();
        modelAndView.addObject("items", items);
        String fullName=userService.getNameOfUser(userSession.getId());
        modelAndView.addObject("fullName", fullName);
        return modelAndView;
    }

    @GetMapping("add-to-cart")
    public ModelAndView addToCart(@RequestParam("productId") int productId){
        ModelAndView modelAndView = new ModelAndView("dashboard");
        if (userSession.getId()<=0){
            return new ModelAndView("index");
        }
        List<Product> productList = productService.findAllProducts();
        modelAndView.addObject("productList", productList);
        userSession.addToCart(productId);
        System.out.println(userSession.getCart());
        items = userSession.getCartSize();
        modelAndView.addObject("items",items);
        return new ModelAndView("redirect:dashboard");
    }

    @GetMapping("/cart")
    public ModelAndView getCart(){
        ModelAndView modelAndView = new ModelAndView("cart");
        if (userSession.getId()<=0){
            return new ModelAndView("index");
        }
        List<Product> dataBaseProducts = productService.findAllProducts();
        List<CartProducts> cartProducts = new ArrayList<>();
        double totalOrderAmount = 0;

        for (int idProductCart : userSession.getCart().keySet()){
            for (Product product: dataBaseProducts){
                if (product.getId() == idProductCart){
                    CartProducts cartProduct = new CartProducts();
                    cartProduct.setQuantity(userSession.getCart().get(idProductCart));
                    cartProduct.setId(product.getId());
                    cartProduct.setCategory(product.getCategory());
                    cartProduct.setName(product.getName());
                    cartProduct.setCost(product.getCost());
                    cartProduct.setTotalCost(userSession.getCart().get(idProductCart)*product.getCost());
                    totalOrderAmount = totalOrderAmount+userSession.getCart().get(idProductCart)*product.getCost();
                    cartProducts.add(cartProduct);
                }
            }
        }
        modelAndView.addObject("productList", cartProducts);
        modelAndView.addObject("items", items);
        modelAndView.addObject("totalOrderAmount", totalOrderAmount);
        return modelAndView;
    }

    @GetMapping("logout")
    public ModelAndView logout(){
        userSession.setId(0);
        return new ModelAndView("index");
    }

    @PostMapping("/sendOrder")
    public ModelAndView sendOrder(){
        ModelAndView modelAndView = new ModelAndView("orderSucces");

        List<Product> dataBaseProducts = productService.findAllProducts();
        Order order = new Order();

        if(userSession.getCart().isEmpty()){
            ModelAndView modelAndView1 = new ModelAndView("cart");
            modelAndView1.addObject("message","You cannot send the order! Your cart is empty!");
            return modelAndView1;
        }

        for (int idProductCart : userSession.getCart().keySet()){
            for(Product product : dataBaseProducts){
                if (product.getId() == idProductCart) {
                    OrderLines orderLines = new OrderLines();
                    orderLines.setProductId(idProductCart);
                    orderLines.setQuantity(userSession.getCart().get(idProductCart)); //cart quantity
                    orderLines.setTotalPrice(userSession.getCart().get(idProductCart) * product.getCost()); //total cost per category
                    order.setUserId(userSession.getId());
                    order.setAddress(userService.getAdress(userSession.getId()));
                    orderLines.setOrder(order);
                    orderLinesDAO.save(orderLines);
                }
            }
        }

        userSession.getCart().clear();
        modelAndView.addObject("items", items);
        return modelAndView;

    }

    @GetMapping("/details")
    public ModelAndView getProductDetails(@RequestParam("productId") int productId){
        ModelAndView modelAndView = new ModelAndView("productDetails");
        if(userSession.getId()<=0){
            return new ModelAndView("index");
        }
        Product p = productService.getProductDetailsById(productId);
        modelAndView.addObject("p", p);
        modelAndView.addObject("items", items);
        return modelAndView;
    }

    @GetMapping("/category")
    public ModelAndView getProductDetails(@RequestParam("category") String category){
        ModelAndView modelAndView = new ModelAndView("dashboardCategory");
        if(userSession.getId()<=0){
            return new ModelAndView("index");
        }
        List<Product> productList = productService.getProductDetailsByCategory(category);
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("items", items);
        return modelAndView;
    }

    @GetMapping("/history")
    public ModelAndView showOrderHistory(){
        ModelAndView modelAndView = new ModelAndView("orderHistory");
        List<Order> orders = orderService.getOrdersByUserId(userSession.getId());
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("items", items);
        return modelAndView;
    }

    @GetMapping("/orderDetails")
    public ModelAndView showOrderDetails(@RequestParam("orderId") int orderId){
        ModelAndView modelAndView = new ModelAndView("orderDetails");
        Iterable<OrderLines> orderLines = orderLinesDAO.findAll(); //read all the order and iterate through her
        List<OrderDetails> orderDetailsPerUser = new ArrayList<>();

        for (OrderLines orderLinesDetails : orderLines){
            if (orderLinesDetails.getOrder().getId() == orderId){
                Product product = productService.getProductDetailsById(orderLinesDetails.getProductId());
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setProductName(product.getName());
                orderDetails.setPricePerUnit(product.getCost());
                orderDetails.setQuantity(orderLinesDetails.getQuantity());
                orderDetails.setTotalPrice(orderLinesDetails.getTotalPrice());
                orderDetails.setCategory(product.getCategory());
                orderDetails.setImgSrc(product.getImgSrc());
                orderDetailsPerUser.add(orderDetails);

            }

        }
        modelAndView.addObject("orderLines", orderDetailsPerUser);
        modelAndView.addObject("items", items);
        return modelAndView;
    }
}
