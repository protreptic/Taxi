package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 02.06.16.
 */
public class EmptyRequestSerializer implements JsonSerializer<EmptyRequest> {

    @Override
    public JsonElement serialize(EmptyRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("api_version", src.getApiVersion());
        jsonObject.addProperty("token", src.getToken());

        return jsonObject;
    }

}
