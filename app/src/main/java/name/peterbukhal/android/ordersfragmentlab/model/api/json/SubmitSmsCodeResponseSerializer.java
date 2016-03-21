package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitSmsCodeResponse;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitSmsCodeResponseSerializer implements JsonSerializer<SubmitSmsCodeResponse> {

    @Override
    public JsonElement serialize(SubmitSmsCodeResponse src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("token", src.getToken());

        return object;
    }

}
