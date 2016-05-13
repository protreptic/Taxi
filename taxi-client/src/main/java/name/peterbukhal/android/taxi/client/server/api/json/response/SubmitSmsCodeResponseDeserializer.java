package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitSmsCodeResponseDeserializer implements JsonDeserializer<SubmitSmsCodeResponse> {

    @Override
    public SubmitSmsCodeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        SubmitSmsCodeResponse response = new SubmitSmsCodeResponse();
        response.setToken(object.get("token").getAsString());

        return response;
    }

}
