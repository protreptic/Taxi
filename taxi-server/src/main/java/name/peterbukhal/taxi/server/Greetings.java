package name.peterbukhal.taxi.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by
 *      admin on 03/06/16.
 */
public class Greetings {

    public static class Message {

        public final String message;

        public Message(String message) {
            this.message = message;
        }

    }

    @GET
    @Path("/greetings")
    @Produces(MediaType.APPLICATION_JSON)
    public String greetings() {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        return "Hello!";//gson.toJson(new Message("Hello!"));
    }

}
