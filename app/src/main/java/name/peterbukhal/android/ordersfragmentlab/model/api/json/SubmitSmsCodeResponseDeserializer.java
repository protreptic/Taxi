package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitSmsCodeResponse;

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
