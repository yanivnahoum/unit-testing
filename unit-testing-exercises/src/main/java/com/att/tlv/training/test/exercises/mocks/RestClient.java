package com.att.tlv.training.test.exercises.mocks;

import java.util.Objects;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    private static final Response SERVER_ERROR_RESPONSE = Response.serverError().build();
    private final WebTargetProvider webTargetProvider;

    public RestClient(WebTargetProvider webTargetProvider) {
        this.webTargetProvider = Objects.requireNonNull(webTargetProvider);
    }
    
    /**
     * Makes a GET request to the specified baseUrl/path.
     * @param baseUrl must be a valid URL, and not {@code null}.
     * @param path the path to be concatenated to the baseUrl.
     * @return the server's response or an HTTP 500 response in case of an invalid baseUrl.
     */
    public Response get(String baseUrl, String path) {
        Response response;
        try {
            response = tryGet(baseUrl, path);
        }
        catch (Exception e) {
            response = SERVER_ERROR_RESPONSE;
        }
        return response;
    }

    private Response tryGet(String baseUrl, String path) {
        return webTargetProvider.get(baseUrl)
                .path(path)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();
    }
}
