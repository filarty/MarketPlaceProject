package com.filarty.zoomarket.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String description;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String username;
    private LocalDateTime time;
    @Column(columnDefinition = "SMALLINT")
    private float rate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @PrePersist
    public void setTime() {
        this.time = LocalDateTime.now();
    }

    public void setImages(Image image) {
        image.setProduct(this);
        images.add(image);
    }

}
