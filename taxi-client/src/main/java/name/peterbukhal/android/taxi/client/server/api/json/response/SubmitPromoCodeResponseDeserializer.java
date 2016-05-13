package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 09.05.16.
 */
public class SubmitPromoCodeResponseDeserializer implements JsonDeserializer<SubmitPromoCodeResponse> {

    @Override
    public SubmitPromoCodeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Boolean status = json.getAsJsonObject().get("status").getAsBoolean();

        return new SubmitPromoCodeResponse(status);
    }

}
