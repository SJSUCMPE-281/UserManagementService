package com.marketplace.usermanagement.models;

import lombok.Data;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Sale {
    String orderId;
    @OneToMany
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
