package com.marketplace.usermanagement.models;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    String total;
    List<Item> items;
    boolean receipt;
    String name;
    String address01;
    String address02;
    String city;
    String state;
    String zip;
    String subtotal;
    String tax;
}
