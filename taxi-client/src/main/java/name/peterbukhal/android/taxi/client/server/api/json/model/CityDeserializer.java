package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.model.impl.CityImpl;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CityDeserializer implements JsonDeserializer<City> {

    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject object = new JsonObject();

        return new CityImpl(
                object.get("city_id").getAsLong(),
                object.get("city_name").getAsString());
    }
}
