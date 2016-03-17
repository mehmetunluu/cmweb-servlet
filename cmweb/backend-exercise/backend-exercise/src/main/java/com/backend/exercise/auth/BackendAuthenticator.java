package com.backend.exercise.auth;

import com.backend.exercise.hibernate.entity.impl.User;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BackendAuthenticator implements io.dropwizard.auth.Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        User user =InMemoryUser.getInMemoryUser().getUserByName(credentials.getUsername());

        if (user!=null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
