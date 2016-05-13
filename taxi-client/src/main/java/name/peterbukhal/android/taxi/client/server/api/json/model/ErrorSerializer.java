package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.server.api.json.Error;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class ErrorSerializer implements JsonSerializer<Error> {

    @Override
    public JsonElement serialize(Error src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("code", src.getCode());
        object.addProperty("message", src.getMessage());
        object.addProperty("message_human", src.getMessageHuman());

        return object;
    }
}
