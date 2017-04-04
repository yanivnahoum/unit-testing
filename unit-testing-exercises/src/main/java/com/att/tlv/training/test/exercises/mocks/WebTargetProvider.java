package com.att.tlv.training.test.exercises.mocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public class WebTargetProvider {
    
    public final Client client;
    private final Map<String, WebTarget> targets = new HashMap<>();
    
    public WebTargetProvider(Client client) {
        this.client = Objects.requireNonNull(client);
    }

    /**
     * Gets a {@code WebTarget} referencing the specified URL.
     *
     * @param url web resource URI. Must not be {@code null}.
     * @return web resource target bound to the provided URL.
     * @throws IllegalArgumentException in case the supplied string is not a valid URL.
     * @throws NullPointerException     in case the supplied argument is {@code null}.
     */
    public WebTarget get(String url) {
        return targets.computeIfAbsent(url, this::create);
    }
    
    private WebTarget create(String url) {
        return client.target(url);
    }
}