package com.example.rest.client;

import com.backend.exercise.hibernate.client.MockClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by M.UNLU on 03.03.2016.
 */
public class MockClientTest {

    @Test
    public void mockClient(){
        MockClient mockClient = new MockClient();
        boolean response =mockClient.connect();
        Assert.assertTrue(response);
    }
}
