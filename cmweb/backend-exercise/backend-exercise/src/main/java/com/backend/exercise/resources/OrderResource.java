package com.backend.exercise.resources;

import com.backend.exercise.BackendExerciseApplication;
import com.backend.exercise.hibernate.entity.impl.User;
import com.backend.exercise.hibernate.dao.OrderDAO;
import com.backend.exercise.hibernate.dao.PersonDAO;
import com.backend.exercise.hibernate.entity.impl.Order;
import com.backend.exercise.hibernate.entity.impl.Person;
import com.backend.exercise.hibernate.entity.impl.Product;
import io.dropwizard.auth.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by M.UNLU on 01.03.2016.
 */

@Produces(MediaType.APPLICATION_JSON)
@Path("/order")
public class OrderResource extends BaseResource {

    private OrderDAO orderDAO=null;
    private PersonDAO personDAO =null;

    public OrderResource(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @POST
    @Path("orders")
    public List<Order> getOrderAll(@Auth User user){
            return  orderDAO.findAll();
    }

    @PUT
    public  void putOrder(@Auth User user, @PathParam("orderName") String  OrderName, @PathParam("personOid") int  personOid, List<Product> products){
        Order order = new Order();
        personDAO = new PersonDAO(BackendExerciseApplication.getHibernateBundle().getSessionFactory());
        Person person =personDAO.findById(Long.valueOf(personOid)).get();
        order.setProducts(products);
        order.setPerson(person);
        orderDAO.create(order);
    }
}
