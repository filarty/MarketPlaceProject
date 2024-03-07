package com.filarty.zoomarket.repository;

import com.filarty.zoomarket.models.Image;
import com.filarty.zoomarket.prop.MinioProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MinIORepositoryImpl implements MinIORepository{
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    @Override
    @SneakyThrows
    public String saveImage(InputStream inputStream) {
        String fileName = UUID.randomUUID() + ".jpg";
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(minioProperties.getBucket())
                .contentType("image/jpg")
                .object(fileName)
                .build());
        return fileName;
    }

    @Override
    public void deleteImage(String filename) {
    }

    @Override
    @SneakyThrows
    public InputStream getImage(String filename) {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(minioProperties.getBucket())
                .object(filename)
                .build());
    }

    @Override
    public void updateImage(String filename) {

    }
}
