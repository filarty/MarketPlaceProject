package com.filarty.zoomarket.controllers;

import com.filarty.zoomarket.config.MyUserDetails;
import com.filarty.zoomarket.models.Product;
import com.filarty.zoomarket.models.User;
import com.filarty.zoomarket.services.ImageService;
import com.filarty.zoomarket.services.ProductService;
import com.filarty.zoomarket.services.RabbitMQService;
import com.filarty.zoomarket.services.UserService;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.Path;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ProductController {
    private final ProductService productService;
    private final ImageService imageService;
    private final UserService userService;
    private final RabbitMQService rabbitMQService;
    @GetMapping("/")
    public String main_page(Model model, Principal principal) {
        model.addAttribute("products", productService.getProducts());
        User user = userService.getUserByPrincipal(principal);
        if (user != null) {
            model.addAttribute("user", userService.getUserByPrincipal(principal));
        }
        return "index";
    }

    @PostMapping("/add_product")
    public String add_product(Product product, @RequestParam MultipartFile file){
        product.setImages(imageService.saveImage(file));
        productService.createProduct(product);
        return "redirect:/";
    }

    @GetMapping ("/get_product/{product_id}")
    public String get_product(Model model, @PathVariable(name = "product_id") Long product_id, Principal principal){
        Product product = productService.findProductById(product_id).orElse(new Product());
        model.addAttribute("product", product);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "product_page";
    }
}
