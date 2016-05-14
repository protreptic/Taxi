package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.Cities;
import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.model.impl.CitiesImpl;
import name.peterbukhal.android.taxi.client.model.impl.CityImpl;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CitiesDeserializer implements JsonDeserializer<Cities> {

    @Override
    public Cities deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject object = json.getAsJsonObject();

        List<City> cities = new ArrayList<>();

        for (int count = 0; count < object.getAsJsonArray("cities").size(); count++) {
            cities.add(context.<CityImpl>deserialize(object.getAsJsonArray("cities").get(count), City.class));
        }

        return new CitiesImpl(cities);
    }

}
