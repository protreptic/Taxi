package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 12.05.16.
 */
public class UnregisterDeviceResponseDeserializer implements JsonDeserializer<UnregisterDeviceResponse> {

    @Override
    public UnregisterDeviceResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        return new UnregisterDeviceResponse(object.get("status").getAsBoolean());
    }

}
