package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.server.api.json.Error;

/**
 * Created by petronic on 21.03.16.
 */
public class ErrorDeserializer implements JsonDeserializer<Error> {

    @Override
    public Error deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Error error = new Error();
        error.setCode(object.get("code").getAsInt());
        error.setMessage(object.get("message").getAsString());
        error.setMessageHuman(object.get("message_human").getAsString());

        return error;
    }

}
