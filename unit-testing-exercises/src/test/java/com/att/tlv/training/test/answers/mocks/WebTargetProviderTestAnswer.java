package com.att.tlv.training.test.answers.mocks;

import com.att.tlv.training.test.exercises.mocks.WebTargetProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebTargetProviderTestAnswer {
    
    private static final String URL = "http://web.att.com/api/conferences";
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
        // The following verification is actually not needed since we already made sure we got the webTarget.
        // How else could the code have returned it??
        verify(client).target(URL);
    }
    
    @Test
    public void testGetExisting() {
        when(client.target(URL)).thenReturn(webTarget);
        
        webTargetProvider.get(URL);
        WebTarget actualTarget = webTargetProvider.get(URL);
        
        assertThat(actualTarget).isSameAs(webTarget);
        verify(client).target(URL);
        // We can either verify and then call this, or just call verify once at the end.
        verifyNoMoreInteractions(client);
    }
    
    @Test
    public void testGetWithNullURL() {
        NullPointerException ex = new NullPointerException("This exception was intentionally thrown by a mock");
        String url = null;
        when(client.target(url)).thenThrow(ex);
        
        assertThatThrownBy(() -> webTargetProvider.get(url)).isSameAs(ex);
        
        // Another option (which doesn't use stubbing for exceptions)
        Client newClient = ClientBuilder.newClient();
        WebTargetProvider provider = new WebTargetProvider(newClient);
        assertThatNullPointerException().isThrownBy(() -> provider.get(url));
    }
}
