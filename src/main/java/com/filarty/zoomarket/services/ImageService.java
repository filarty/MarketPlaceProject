package com.filarty.zoomarket.services;


import com.filarty.zoomarket.models.Image;
import com.filarty.zoomarket.models.Product;
import com.filarty.zoomarket.repository.DatabaseImageRepository;
import com.filarty.zoomarket.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService implements IImageService{
    private final ImageRepository imageRepository;
    private final ProductService productService;

    public List<Image> getAllImagesByPruductId(Long id) {
        return productService.findProductById(id).orElse(new Product()).getImages();
    }
    @Override
    public Image saveImage(MultipartFile file) {
        log.info("file : {}", file.getOriginalFilename());
        return imageRepository.save(file);
    }

    public InputStream getImageByPath(String path){
        return imageRepository.getImage(path);
    }
}
