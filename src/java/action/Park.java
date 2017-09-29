/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import bean.Auto;
import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jesus-my-lord
 */
@Path("park")
@Stateless
public class Park {

    @EJB
    StateApplication stateApplication;

    private static final Logger LOG = Logger.getLogger(Park.class.getName());

    @PUT
    @Path("in")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String enter(String plainJson) {
        LOG.log(Level.INFO, "resultadd={0}", plainJson);
        JsonReader jsonReader = Json.createReader(new ByteArrayInputStream(plainJson.getBytes()));
        JsonObject jsonObject = jsonReader.readObject();
        String number = jsonObject.getString("number");
        String model = jsonObject.getString("model");
        String startTime = jsonObject.getString("startTime");

        boolean isParked = stateApplication.parkingNewCar(new Auto(number, model, startTime));

        LOG.log(Level.INFO, "Number = {0} Model={1} started parking at {2}", new String[]{number, model, startTime});
        JsonObject resultJson = Json.createObjectBuilder()
                .add("parked", isParked).build();
        return resultJson.toString();
    }

    @POST
    @Path("out")
    @Consumes(MediaType.APPLICATION_JSON)
    public String exit(String plainJson) {

        JsonReader jsonReader = Json.createReader(new ByteArrayInputStream(plainJson.getBytes()));
        JsonObject jsonObject = jsonReader.readObject();
        String number = jsonObject.getString("number");
        int payment = jsonObject.getInt("payment");
        String endTime = jsonObject.getString("endTime");
        stateApplication.leaveParking(number, payment, endTime);
        LOG.log(Level.INFO, "Number = {0} Payd={1} end parking at {2}", new Object[]{number, payment, endTime});
        JsonObject resultJson = Json.createObjectBuilder()
                .add("leaved", true).build();
        return resultJson.toString();
    }

}
