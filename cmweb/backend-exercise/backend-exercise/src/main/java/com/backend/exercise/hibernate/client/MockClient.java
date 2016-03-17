package com.backend.exercise.hibernate.client;

import org.glassfish.jersey.client.ClientResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
/**
 * Created by M.UNLU on 01.03.2016.
 */
public class MockClient {
    // TODO: 01.03.2016  https://demo3033169.mockable.io/xml/products


    private void connectToClient(){

        Client client = Client.create();
        WebResource webResource = client.resource("https://demo3033169.mockable.io/test");
        ClientResponse response = webResource.accept("text/html").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        response.getEntity();
    }

    public boolean connect() {
        connectToClient();
        return  true;
    }
}
