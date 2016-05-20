package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.model.impl.CityImpl;
import name.peterbukhal.android.taxi.client.model.impl.PointImpl;
import name.peterbukhal.android.taxi.client.model.impl.SpnPointImpl;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CityDeserializer implements JsonDeserializer<City> {

    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        String[] inAppPayMethods = new String[0];

        if (object.get("inapp_pay_methods") != null) {
            JsonArray arr = object.get("inapp_pay_methods").getAsJsonArray();

            inAppPayMethods = new String[arr.size()];

            for (int i = 0; i < arr.size(); i++) {
                inAppPayMethods[i] = arr.get(i).getAsString();
            }
        }

        Long experimental_econom_plus = 0L;

        if (object.get("experimental_econom_plus") != null) {
            experimental_econom_plus = object.get("experimental_econom_plus").getAsLong();
        }

        return new CityImpl(
                object.get("city_id").getAsLong(),
                object.get("city_name").getAsString(),
                object.get("city_api_url").getAsString(),
                new PointImpl(
                        object.get("city_latitude").getAsDouble(),
                        object.get("city_longitude").getAsDouble()),
                new SpnPointImpl(
                        object.get("city_spn_latitude").getAsDouble(),
                        object.get("city_spn_longitude").getAsDouble()),
                object.get("transfers").getAsBoolean(),
                inAppPayMethods,
                experimental_econom_plus);
    }

}
