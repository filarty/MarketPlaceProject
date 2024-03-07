package com.filarty.zoomarket.repository;

import com.filarty.zoomarket.models.Image;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.List;

public interface MinIORepository {
    String saveImage(InputStream inputStream);
    void deleteImage(String filename);
    InputStream getImage(String filename);
    void updateImage(String filename);

}
