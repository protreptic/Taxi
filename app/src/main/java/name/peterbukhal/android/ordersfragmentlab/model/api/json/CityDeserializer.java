package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.City;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CityDeserializer implements JsonDeserializer<City> {

    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = new JsonObject();

        Long id = object.get("city_id").getAsLong();
        String name = object.get("city_name").getAsString();

        return new City(id, name);
    }
}
