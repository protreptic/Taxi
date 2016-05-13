package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 12.05.16.
 */
public class UnregisterDeviceRequestSerializer implements JsonSerializer<UnregisterDeviceRequest> {

    @Override
    public JsonElement serialize(UnregisterDeviceRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("token", src.getToken());

        return object;
    }

}
