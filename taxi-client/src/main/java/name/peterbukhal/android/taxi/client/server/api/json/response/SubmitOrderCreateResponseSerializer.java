package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class SubmitOrderCreateResponseSerializer implements JsonSerializer<SubmitOrderCreateResponse> {

    @Override
    public JsonElement serialize(SubmitOrderCreateResponse src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("order_id", src.getOrderId());

        return object;
    }

}
