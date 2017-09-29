/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import bean.Auto;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jesus-my-lord
 */
@Path("result")
@Stateless
public class Result {

    @EJB
    StateApplication stateApplication;

    @GET
    @Path("exist/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String isParking(@PathParam("number") String number) {
        try {
            System.out.println("number=" + URLDecoder.decode(number, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonObject resultJson = Json.createObjectBuilder()
                .add("exist", stateApplication.isParked(number)).build();
        return resultJson.toString();
    }

    @GET
    @Path("money/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public String moneyReport(@PathParam("date") String date) {
        JsonObject resultJson = Json.createObjectBuilder()
                .add("sum", stateApplication.money(date)).build();
        return resultJson.toString();

    }

    @GET
    @Path("parked")
    @Produces(MediaType.APPLICATION_JSON)
    public String listParkedCars() {
        JsonArrayBuilder arrays = Json.createArrayBuilder();

        stateApplication.listParked().forEach((auto) -> {
            arrays.add(Json.createObjectBuilder()
                    .add("number", auto.getNumber())
                    .add("model", auto.getModel()));
        });

        JsonObject resultJson = Json.createObjectBuilder()
                .add("parked", arrays).build();

        return resultJson.toString();
    }

    @GET
    @Path("free")
    @Produces(MediaType.APPLICATION_JSON)
    public String freePlace() {
        JsonObject resultJson = Json.createObjectBuilder()
                .add("freePlace", stateApplication.freePlace()).build();
        return resultJson.toString();
    }

}
