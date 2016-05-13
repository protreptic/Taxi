package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 09.05.16.
 */
public class SubmitPromoCodeRequestDeserializer implements JsonDeserializer<SubmitPromoCodeRequest> {

    @Override
    public SubmitPromoCodeRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String token = json.getAsJsonObject().get("token").getAsString();
        String promoCode = json.getAsJsonObject().get("promo_code").getAsString();

        return new SubmitPromoCodeRequest(token, promoCode);
    }

}
