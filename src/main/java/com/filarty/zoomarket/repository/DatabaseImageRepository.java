package com.filarty.zoomarket.repository;

import com.filarty.zoomarket.models.Image;
import org.springframework.data.repository.ListCrudRepository;

public interface DatabaseImageRepository extends ListCrudRepository<Image, Long> {
}
