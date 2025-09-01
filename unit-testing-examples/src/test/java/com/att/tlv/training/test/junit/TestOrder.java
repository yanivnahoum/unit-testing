package com.att.tlv.training.test.junit;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class TestOrder {

    @Order(1)
    @Test
    void first() {
    }

    @Order(2)
    @Test
    void second() {
    }

    @Order(3)
    @Test
    void third() {
    }

}
