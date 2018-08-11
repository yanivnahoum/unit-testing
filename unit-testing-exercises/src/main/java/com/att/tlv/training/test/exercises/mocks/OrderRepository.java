package com.att.tlv.training.test.exercises.mocks;

import com.att.tlv.training.test.exercises.data.Order;

public interface OrderRepository {

    void saveOrder(Order order);
    void deleteOrder(long id);
}


