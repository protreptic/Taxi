package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.api.request.QueryOrdersRequest;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class QueryOrdersRequestSerializer implements JsonSerializer<QueryOrdersRequest> {

    @Override
    public JsonElement serialize(QueryOrdersRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("token", src.getToken());
        object.addProperty("offset", src.getOffset());
        object.addProperty("count", src.getCount());
        object.addProperty("type", src.getType().getValue());

        return object;
    }

}
