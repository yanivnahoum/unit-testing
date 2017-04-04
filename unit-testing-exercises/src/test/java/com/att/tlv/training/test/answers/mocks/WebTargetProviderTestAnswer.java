package com.att.tlv.training.test.answers.mocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.att.tlv.training.test.exercises.mocks.WebTargetProvider;

@RunWith(MockitoJUnitRunner.class)
public class WebTargetProviderTestAnswer {
    
    private static final String URL = "http://web.att.com/api/conferences";
    private static final String INVALID_URL = "bla bla";
    private WebTargetProvider webTargetProvider;
    @Mock private Client client;
    @Mock private WebTarget webTarget;

    @Before
    public void setUp() {
        webTargetProvider = new WebTargetProvider(client);
    }

    @Test
    public void testGetNew() {
        when(client.target(URL)).thenReturn(webTarget);
        
        WebTarget actualTarget = webTargetProvider.get(URL);
        
        assertThat(actualTarget).isSameAs(webTarget);
        verify(client).target(URL);
    }
    
    @Test
    public void testGetExisting() {
        when(client.target(URL)).thenReturn(webTarget);
        
        WebTarget actualTarget = webTargetProvider.get(URL);
        verify(client).target(URL);
        
        actualTarget = webTargetProvider.get(URL);
        
        assertThat(actualTarget).isSameAs(webTarget);
        // We can either verify and then call this, or just call verify once at the end.
        verifyNoMoreInteractions(client);
    }
    
    @Test
    public void testGetWithInvalidURL() {
        IllegalArgumentException ex = new IllegalArgumentException("This exception was intentionally thrown by a mock");
        when(client.target(INVALID_URL)).thenThrow(ex);
        
        assertThatThrownBy(() -> webTargetProvider.get(INVALID_URL)).isSameAs(ex);
    }
    
    @Test
    public void testGetWithNullURL() {
        NullPointerException ex = new NullPointerException("This exception was intentionally thrown by a mock");
        String url = null;
        when(client.target(url)).thenThrow(ex);
        
        assertThatThrownBy(() -> webTargetProvider.get(url)).isSameAs(ex);
    }
}
