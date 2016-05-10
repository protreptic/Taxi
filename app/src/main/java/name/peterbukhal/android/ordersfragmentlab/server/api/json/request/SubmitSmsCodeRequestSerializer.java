package name.peterbukhal.android.ordersfragmentlab.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitSmsCodeRequestSerializer implements JsonSerializer<SubmitSmsCodeRequest> {

    @Override
    public JsonElement serialize(SubmitSmsCodeRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("name", src.getName());
        object.addProperty("phone_number", src.getPhoneNumber());
        object.addProperty("source", src.getSource());
        object.addProperty("sms_code", src.getSmsCode());
        object.addProperty("promo_code", src.getPromoCode());

        return object;
    }

}
