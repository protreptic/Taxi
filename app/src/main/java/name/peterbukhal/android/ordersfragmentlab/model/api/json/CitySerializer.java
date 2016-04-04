package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.impl.CityImpl;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CitySerializer implements JsonSerializer<CityImpl> {

    @Override
    public JsonElement serialize(CityImpl src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("city_id", src.getId());
        object.addProperty("city_name", src.getName());

        return object;
    }

}
