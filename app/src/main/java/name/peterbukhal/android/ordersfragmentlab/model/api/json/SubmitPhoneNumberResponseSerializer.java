package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitPhoneNumberResponse;

/**
 * Created by petronic on 21.03.16.
 */
public class SubmitPhoneNumberResponseSerializer implements JsonSerializer<SubmitPhoneNumberResponse> {

    @Override
    public JsonElement serialize(SubmitPhoneNumberResponse src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

}
