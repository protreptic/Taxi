package name.peterbukhal.android.taxi.client.server.api.json.request;

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
public class SubmitSmsCodeRequestDeserializer implements JsonDeserializer<SubmitSmsCodeRequest> {

    @Override
    public SubmitSmsCodeRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        SubmitSmsCodeRequest request = new SubmitSmsCodeRequest();
        request.setName(object.get("name").getAsString());
        request.setPhoneNumber(object.get("phone_number").getAsString());
        request.setSource(object.get("source").getAsInt());
        request.setSmsCode(object.get("sms_code").getAsInt());
        request.setPromoCode(object.get("promo_code").getAsString());

        return request;
    }

}
