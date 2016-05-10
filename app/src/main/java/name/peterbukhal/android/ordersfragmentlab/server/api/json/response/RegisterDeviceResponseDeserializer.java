package name.peterbukhal.android.ordersfragmentlab.server.api.json.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class RegisterDeviceResponseDeserializer implements JsonDeserializer<RegisterDeviceResponse> {

    @Override
    public RegisterDeviceResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        RegisterDeviceResponse response = new RegisterDeviceResponse();
        response.setStatus(object.get("status").getAsBoolean());

        return response;
    }

}
