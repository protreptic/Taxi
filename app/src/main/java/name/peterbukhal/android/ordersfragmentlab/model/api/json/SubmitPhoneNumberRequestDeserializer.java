package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitPhoneNumberRequest;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitPhoneNumberRequestDeserializer implements JsonDeserializer<SubmitPhoneNumberRequest> {

    @Override
    public SubmitPhoneNumberRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String phoneNumber = json.getAsJsonObject().get("phone_number").getAsString();
        String promoCode = json.getAsJsonObject().get("promo_code").getAsString();

        return new SubmitPhoneNumberRequest(phoneNumber, promoCode);
    }
}