package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 01.06.16.
 */
public class QueryCitiesRequestSerializer implements JsonSerializer<QueryCitiesRequest> {

    @Override
    public JsonElement serialize(QueryCitiesRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("api_version", src.getApiVersion());

        return jsonObject;
    }

}
