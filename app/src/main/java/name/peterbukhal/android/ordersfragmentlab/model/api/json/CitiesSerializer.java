package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.Cities;
import name.peterbukhal.android.ordersfragmentlab.model.City;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CitiesSerializer implements JsonSerializer<Cities> {

    @Override
    public JsonElement serialize(Cities src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray cities = new JsonArray();

        for (City city : src.getCities()) {
            cities.add(context.serialize(city));
        }

        JsonObject object = new JsonObject();
        object.add("cities", cities);

        return object;
    }

}
