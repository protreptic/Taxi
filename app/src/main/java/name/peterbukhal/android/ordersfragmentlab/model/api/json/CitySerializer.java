package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.City;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CitySerializer implements JsonSerializer<City> {

    @Override
    public JsonElement serialize(City src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("city_id", src.getId());
        object.addProperty("city_name", src.getName());

        return object;
    }

}
