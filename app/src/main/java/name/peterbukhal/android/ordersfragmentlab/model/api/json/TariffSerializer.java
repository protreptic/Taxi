package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.Tariff;

/**
 * Created by
 * petronic on 25.03.16.
 */
public class TariffSerializer implements JsonSerializer<Tariff> {

    @Override
    public JsonElement serialize(Tariff src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("tariff_id", src.getId());
        object.addProperty("tariff_name", src.getName());
        object.addProperty("tariff_rate", src.getRate());
        object.addProperty("tariff_order", src.getOrder());

        return object;
    }
}
