package com.marketplace.usermanagement.models;

import lombok.Data;

@Data
public class Item {
    String text;
    int quantity;
    String image;
    String price;
}
