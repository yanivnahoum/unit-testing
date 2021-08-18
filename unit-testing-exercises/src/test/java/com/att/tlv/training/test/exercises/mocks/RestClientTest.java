package com.att.tlv.training.test.exercises.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestClientTest {
    
    private RestClient restClient;

    @BeforeEach
    void setUp() {
        // TODO init
    }

    @Test
    void get_shouldCallSetRequestPath_andHeader_andReturnOkResponse() {
        // TODO test RestClient.get() happy scenario
        //  Verify all required behavior
    }
    
    @Test
    void whenClientThrowsException_get_shouldReturnInternalServerErrorResponse() {
        // TODO test RestClient.get() unhappy scenario - webTargetProvider.get() throws.
        //  Verify all required behavior
    }
}