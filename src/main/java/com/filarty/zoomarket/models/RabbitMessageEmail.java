package com.filarty.zoomarket.models;


import lombok.Data;

import java.io.Serializable;

@Data
public class RabbitMessageEmail implements Serializable {
    private String body;
    private String to;
}
