package com.filarty.zoomarket.services;

import com.filarty.zoomarket.models.Image;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public interface IImageService {
    List<Image> getAllImagesByPruductId(Long id);
    Image saveImage(MultipartFile file);
}
