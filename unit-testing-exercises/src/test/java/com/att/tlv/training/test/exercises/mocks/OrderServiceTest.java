package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Order;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {

    private static final Order ORDER = new Order(1L, "Nike Shoes");
    private OrderService orderService;

    @Before
    public void setUp() {
        // TODO set up test
    }

    @Test
    public void placeOrder_shouldCallSaveOrderOnce() {
        // TODO test OrderService.placeOrder()
    }

    @Test
    public void placeMultipleOrders_withCount_shouldCallSaveOrderCountTimes() {
        // TODO test OrderService.placeMultipleOrders() happy flow
    }


    @Test
    public void placeMultipleOrders_withInvalidCount_shouldThrowException_withMessage() {
        // TODO test OrderService.placeMultipleOrders() unhappy flow
    }


    @Test
    public void cancelOrder_shouldCallDeleteOrder() {
        // TODO test OrderService.cancelOrder()
    }
}