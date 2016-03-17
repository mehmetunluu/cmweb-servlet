package com.backend.exercise.resources;

import com.backend.exercise.auth.InMemoryUser;
import com.backend.exercise.hibernate.entity.impl.User;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by M.UNLU on 02.03.2016.
 */
@Path("/authorize")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceAuthentication extends BaseResource {

    @GET
    public Response authroize(@HeaderParam("username") String username, @HeaderParam("password") String password){
        if(!StringUtils.isNotEmpty(username)){
            return  Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(!StringUtils.isNotEmpty(password)){
            return  Response.status(Response.Status.BAD_REQUEST).build();
        }
        // TODO: 02.03.2016  db'den user bilgileri çekilerek bir token nesnesine üretilebilirdi
        if(username.equals("test")&& password.equals("test123!")){
            User user = new User(username);
            InMemoryUser.getInMemoryUser().addAuthUser(user);
            return Response.ok(user).build();
        }

        return  Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
