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
    List<OrderDetails> orderDetails;
    BigDecimal totalPrice;
    BigDecimal taxAmount;
    BigDecimal totalAmount;
    String status;
    String trackingId;
    @OneToOne
    User buyer;
    Date createdAt;
    Date updatedAt;
}
