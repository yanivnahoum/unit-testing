package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Order;

import static com.google.common.base.Preconditions.checkArgument;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(Order order) {
        placeMultipleOrders(order, 1);
    }

    public void placeMultipleOrders(Order order, int count) {
        checkArgument(count > 0, "Number of orders must be greater than 0");
        for (int i = 0; i < count; i++) {
            orderRepository.saveOrder(order);
        }
    }

    public void cancelOrder(long id) {
        orderRepository.deleteOrder(id);
    }
}
