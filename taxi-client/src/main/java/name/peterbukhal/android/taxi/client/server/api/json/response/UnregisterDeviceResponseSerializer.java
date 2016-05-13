package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 12.05.16.
 */
public class UnregisterDeviceResponseSerializer implements JsonSerializer<UnregisterDeviceResponse> {

    @Override
    public JsonElement serialize(UnregisterDeviceResponse src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("status", src.getStatus());

        return object;
    }

}
