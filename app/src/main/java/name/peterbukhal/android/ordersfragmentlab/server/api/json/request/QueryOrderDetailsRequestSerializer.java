package name.peterbukhal.android.ordersfragmentlab.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 09.05.16.
 */
public class QueryOrderDetailsRequestSerializer implements JsonSerializer<QueryOrderDetailsRequest> {

    @Override
    public JsonElement serialize(QueryOrderDetailsRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("token", src.getToken());
        object.addProperty("order_id", src.getOrder().getId());

        return object;
    }

}
