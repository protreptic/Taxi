package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 09.05.16.
 */
public class SubmitPromoCodeResponseSerializer implements JsonSerializer<SubmitPromoCodeResponse> {

    @Override
    public JsonElement serialize(SubmitPromoCodeResponse src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("status", src.getStatus());

        return object;
    }

}
