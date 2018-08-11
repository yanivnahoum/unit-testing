package com.att.tlv.training.test.answers.mocks;

import com.att.tlv.training.test.exercises.data.Order;
import com.att.tlv.training.test.exercises.mocks.OrderRepository;
import com.att.tlv.training.test.exercises.mocks.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTestAnswer {

    private static final Order ORDER = new Order(1L, "Nike Shoes");
    private OrderService orderService;
    @Mock private OrderRepository orderRepository;

    @Before
    public void setUp() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void placeOrder_shouldCallSaveOrderOnce() {
        orderService.placeOrder(ORDER);
        verify(orderRepository).saveOrder(ORDER);
    }

    @Test
    public void placeMultipleOrders_withCount_shouldCallSaveOrderCountTimes() {
        int count = 50;
        orderService.placeMultipleOrders(ORDER, count);
        verify(orderRepository, times(count)).saveOrder(ORDER);
    }

    @Test
    public void placeMultipleOrders_withInvalidCount_shouldThrowException_withMessage() {
        assertThatIllegalArgumentException().isThrownBy(() -> orderService.placeMultipleOrders(ORDER, -1))
                                            .withMessage("Number of orders must be greater than 0");

        verify(orderRepository, never()).saveOrder(ORDER);

        // Or
        verify(orderRepository, never()).saveOrder(any());

        // Or
        verifyZeroInteractions(orderRepository);
    }

    @Test
    public void cancelOrder_shouldCallDeleteOrder() {
        long id = 101L;
        orderService.cancelOrder(id);
        verify(orderRepository).deleteOrder(id);
    }
}