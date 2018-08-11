package com.att.tlv.training.test.answers.mocks;

import com.att.tlv.training.test.exercises.mocks.RestClient;
import com.att.tlv.training.test.exercises.mocks.WebTargetProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestClientTestAnswer {
    
    private static final String URL = "http://web.att.com/api/";
    private static final Response OK_RESPONSE = Response.ok().build();
    private RestClient restClient;
    @Mock 
    private Client client;
    @Mock(answer = Answers.RETURNS_SELF)
    private WebTarget webTarget;
    @Mock(answer = Answers.RETURNS_SELF)
    private Builder builder;


    @Before
    public void setUp() {
        WebTargetProvider webTargetProvider = new WebTargetProvider(client);
        restClient = new RestClient(webTargetProvider);
        when(client.target(URL)).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(builder);
        when(builder.get()).thenReturn(OK_RESPONSE);
    }

    @Test
    public void get_shouldCallSetRequestPath_andHeader_andReturnOkResponse() {
        String path = "conferences";
        Response actualResponse = restClient.get(URL, path);
        
        assertThat(actualResponse).isSameAs(OK_RESPONSE);
        verify(webTarget).path(path);
        verify(builder).accept(MediaType.APPLICATION_JSON_TYPE);
    }
    
    @Test
    public void whenClientThrowsException_get_shouldReturnInternalServerErrorResponse() {
        RuntimeException ex = new RuntimeException("This exception was intentionally thrown by a mock");
        when(client.target(URL)).thenThrow(ex);
        
        String path = "conferences";
        Response actualResponse = restClient.get(URL, path);
        
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getStatus()).isEqualTo(Status.INTERNAL_SERVER_ERROR.getStatusCode());
        verify(builder, never()).get();
    }
}
