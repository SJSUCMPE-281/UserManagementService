package com.marketplace.usermanagement.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Sale {
    String orderId;
    List<OrderDetails> orderDetailsDTO;
    BigDecimal totalPrice;
    BigDecimal taxAmount;
    BigDecimal totalAmount;
    String status;
    String trackingId;
    String buyerId;
    Date createdAt;
    Date updatedAt;
}
