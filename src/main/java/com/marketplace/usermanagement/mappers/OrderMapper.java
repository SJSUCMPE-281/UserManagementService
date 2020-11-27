package com.marketplace.usermanagement.mappers;

import com.marketplace.usermanagement.models.Item;
import com.marketplace.usermanagement.models.Order;
import com.marketplace.usermanagement.models.OrderDetails;
import com.marketplace.usermanagement.models.Sale;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public Order toOrder(Sale sale) {
        Order order = new Order();
        order.setTotal(sale.getTotalAmount().toString());
        order.setSubtotal(sale.getTotalPrice().toString());
        order.setTax(sale.getTaxAmount().toString());
        order.setAddress01(sale.getAddress1());
        order.setAddress02(sale.getAddress2());
        order.setCity(sale.getCity());
        order.setState(sale.getState());
        order.setZip(sale.getZip());
        order.setReceipt(true);
        List<Item> items = new ArrayList<>();
        for(OrderDetails details: sale.getOrderDetailsDTO()) {
            items.add(toItem(details));
        }
        order.setItems(items);
        return order;
    }

    public Item toItem(OrderDetails details) {
        Item item = new Item();
        item.setText(details.getProductName());
        item.setQuantity(details.getQuantity());
        item.setImage(details.getImageUrl());
        item.setPrice(details.getPrice().toString());
        return  item;
    }

}
