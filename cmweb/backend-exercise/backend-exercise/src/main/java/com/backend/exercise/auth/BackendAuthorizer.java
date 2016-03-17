package com.backend.exercise.auth;

import com.backend.exercise.hibernate.entity.impl.User;

public class BackendAuthorizer implements io.dropwizard.auth.Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        if(user.getRoles() != null && user.getRoles().contains(role)) {
            return true;
        }

        return false;
    }
}
