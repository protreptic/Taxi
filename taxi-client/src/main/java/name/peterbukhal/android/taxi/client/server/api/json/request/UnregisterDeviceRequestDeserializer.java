package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 12.05.16.
 */
public class UnregisterDeviceRequestDeserializer implements JsonDeserializer<UnregisterDeviceRequest> {

    @Override
    public UnregisterDeviceRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

}
