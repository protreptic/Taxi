package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class SubmitOrderCreateResponseDeserializer implements JsonDeserializer<SubmitOrderCreateResponse>{

    @Override
    public SubmitOrderCreateResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        return new SubmitOrderCreateResponse(object.get("order_id").getAsLong());
    }

}
