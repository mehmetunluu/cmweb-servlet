package com.backend.exercise.resources;

import com.backend.exercise.hibernate.entity.impl.User;
import com.backend.exercise.hibernate.dao.ProductDAO;
import com.backend.exercise.hibernate.entity.impl.Product;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource extends BaseResource {

    private final ProductDAO productDAO;

    public ProductResource(ProductDAO productDAO ) {
        this.productDAO = productDAO;
    }


    @POST
    @UnitOfWork
    @Path("/getproducts")
    public List<Product> getProduct(@Auth User user) {
        return productDAO.findAll();
    }


    @POST
    @UnitOfWork
    @Path("/{productId}")
    public Optional<Product> getProduct(@Auth User user, @PathParam("productId") Long productId) {
        return productDAO.findById(productId);
    }

}
