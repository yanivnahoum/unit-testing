package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private static final Order ORDER = new Order(1L, "Nike Shoes");
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        // TODO set up test
    }

    @Test
    void placeOrder_shouldCallSaveOrderOnce() {
        // TODO test OrderService.placeOrder()
    }

    @Test
    void placeMultipleOrders_withCount_shouldCallSaveOrderCountTimes() {
        // TODO test OrderService.placeMultipleOrders() happy flow
    }


    @Test
    void placeMultipleOrders_withInvalidCount_shouldThrowException_withMessage() {
        // TODO test OrderService.placeMultipleOrders() unhappy flow
    }


    @Test
    void cancelOrder_shouldCallDeleteOrder() {
        // TODO test OrderService.cancelOrder()
    }
}