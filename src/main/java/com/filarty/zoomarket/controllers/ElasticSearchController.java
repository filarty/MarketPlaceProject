package com.filarty.zoomarket.controllers;


import com.filarty.zoomarket.models.Product;
import com.filarty.zoomarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class ElasticSearchController {
    private final ProductService productService;
    @GetMapping("find_product/{name}")
    public List<Product> find_product(@PathVariable(name = "name") String name) {
        return productService.findProductsByName(name);
    }
}
