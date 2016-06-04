package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 09.05.16.
 */
public final class SubmitPromoCodeRequestSerializer implements JsonSerializer<SubmitPromoCodeRequest> {

    @Override
    public JsonElement serialize(SubmitPromoCodeRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("token", src.getToken());
        object.addProperty("promo_code", src.getPromoCode());

        return object;
    }

}
