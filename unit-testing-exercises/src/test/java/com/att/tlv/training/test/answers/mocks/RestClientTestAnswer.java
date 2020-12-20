package com.att.tlv.training.test.answers.mocks;

import com.att.tlv.training.test.exercises.mocks.RestClient;
import com.att.tlv.training.test.exercises.mocks.WebTargetProvider;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Answers.RETURNS_SELF;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestClientTestAnswer {
    
    private static final String URL = "http://web.att.com/api/";
    private static final Response OK_RESPONSE = Response.ok().build();
    private RestClient restClient;
    @Mock 
    private Client client;
    @Mock(answer = RETURNS_SELF)
    private WebTarget webTarget;
    @Mock(answer = RETURNS_SELF)
    private Builder builder;

    @BeforeEach
    void setUp() {
        WebTargetProvider webTargetProvider = new WebTargetProvider(client);
        restClient = new RestClient(webTargetProvider);
    }

    @Test
    void get_shouldCallSetRequestPath_andHeader_andReturnOkResponse() {
        when(client.target(URL)).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(builder);
        when(builder.get()).thenReturn(OK_RESPONSE);
        String path = "conferences";
        Response actualResponse = restClient.get(URL, path);
        
        assertThat(actualResponse).isSameAs(OK_RESPONSE);
        verify(webTarget).path(path);
        verify(builder).accept(MediaType.APPLICATION_JSON_TYPE);
    }
    
    @Test
    void whenClientThrowsException_get_shouldReturnInternalServerErrorResponse() {
        RuntimeException ex = new RuntimeException("This exception was intentionally thrown by a mock");
        when(client.target(URL)).thenThrow(ex);
        
        String path = "conferences";
        Response actualResponse = restClient.get(URL, path);
        
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatus()).isEqualTo(Status.INTERNAL_SERVER_ERROR.getStatusCode());
        verify(builder, never()).get();
    }
}
