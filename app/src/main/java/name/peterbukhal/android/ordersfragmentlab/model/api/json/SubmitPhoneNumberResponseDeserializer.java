package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitPhoneNumberResponse;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitPhoneNumberResponseDeserializer implements JsonDeserializer<SubmitPhoneNumberResponse> {

    @Override
    public SubmitPhoneNumberResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        SubmitPhoneNumberResponse response = new SubmitPhoneNumberResponse();
        response.setStatus(object.get("status").getAsBoolean());
        response.setSmsCode(object.get("smscode").getAsString());
        response.setSmsCodeLength(object.get("smscode_length").getAsInt());
        response.setRetryDelay(object.get("retry_delay").getAsInt());

        return response;
    }
}
