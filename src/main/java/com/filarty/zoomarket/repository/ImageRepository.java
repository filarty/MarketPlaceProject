package com.filarty.zoomarket.repository;


import com.filarty.zoomarket.models.Image;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ImageRepository {
    private final MinIORepository imageRepository;
    @SneakyThrows
    public Image save(MultipartFile file){
        String imageName = imageRepository.saveImage(file.getInputStream());
        Image image = new Image();
        image.setPath(imageName);
        image.setPreview(true);
        return image;
    }
    @SneakyThrows
    public InputStream getImage(String filename){
        return imageRepository.getImage(filename);
    }


}
