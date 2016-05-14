package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class SubmitOrderCreateRequestSerializer implements JsonSerializer<SubmitOrderCreateRequest> {

    @Override
    public JsonElement serialize(SubmitOrderCreateRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("token", src.getToken());
        object.addProperty("comment", src.getComment());
        object.addProperty("phone_number", src.getPhoneNumber());

        return object;
    }

}
