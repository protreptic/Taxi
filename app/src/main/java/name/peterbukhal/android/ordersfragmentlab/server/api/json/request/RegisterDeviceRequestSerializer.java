package name.peterbukhal.android.ordersfragmentlab.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class RegisterDeviceRequestSerializer implements JsonSerializer<RegisterDeviceRequest> {

    @Override
    public JsonElement serialize(RegisterDeviceRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("token", src.getToken());
        object.addProperty("device_token", src.getDeviceToken());
        object.addProperty("device_platform", src.getDevicePlatform());

        return object;
    }

}
