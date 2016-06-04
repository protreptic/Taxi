package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class RegisterDeviceResponseDeserializer implements JsonDeserializer<RegisterDeviceResponse> {

    @Override
    public RegisterDeviceResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new RegisterDeviceResponse(
                json.getAsJsonObject().get("status").getAsBoolean());
    }

}
