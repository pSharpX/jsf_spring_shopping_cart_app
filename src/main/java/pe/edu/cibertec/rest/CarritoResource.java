package pe.edu.cibertec.rest;

import pe.edu.cibertec.model.CarritoModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by CHRISTIAN on 21/04/2018.
 */
@Path("/carritos")
public class CarritoResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<CarritoModel> getCarritoModels(){
        return new ArrayList<>();
    }

    @GET
    @Path("{idCarrito}")
    @Produces({MediaType.APPLICATION_JSON})
    public CarritoModel getCarritoModel(@PathParam("idCarrito") Long id ){
        return new CarritoModel();
    }
}
