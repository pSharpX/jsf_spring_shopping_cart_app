package pe.edu.cibertec.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by CHRISTIAN on 21/04/2018.
 */
@Path("/hello")
public class HelloResource {
    public static final String CLICHED_MESSAGE = "Hello World!";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHello() {
        return CLICHED_MESSAGE;
    }
}
