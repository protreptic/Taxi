package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import name.peterbukhal.android.ordersfragmentlab.model.Cities;
import name.peterbukhal.android.ordersfragmentlab.model.City;
import name.peterbukhal.android.ordersfragmentlab.model.impl.CityImpl;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CitiesDeserializer implements JsonDeserializer<Cities> {

    @Override
    public Cities deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<City> cities = new ArrayList<>();

        for (int count = 0; count < json.getAsJsonObject().getAsJsonArray("cities").size(); count++) {
            cities.add(context.<CityImpl>deserialize(json.getAsJsonObject().getAsJsonArray("cities").get(count), City.class));
        }

        return new Cities(cities);
    }
}
