package com.filarty.zoomarket.controllers;


import com.filarty.zoomarket.models.Image;
import com.filarty.zoomarket.services.ImageService;
import com.filarty.zoomarket.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;
    @GetMapping("/get_all/{product_id}")
    public List<Image> getImages(@PathVariable Long product_id) {
        return imageService.getAllImagesByPruductId(product_id);
    }
    @PostMapping("/test")
    public String test(@RequestParam MultipartFile file) {
        imageService.saveImage(file);
        return System.getProperty("user.dir") + "\\resources" + "\\img";
    }

    @GetMapping("get_image/{path}")
    @SneakyThrows
    public ResponseEntity<?> get_image(@PathVariable String path){
        byte[] image = imageService.getImageByPath(path).readAllBytes();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(image);
    }

}