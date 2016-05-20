package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.SpnPoint;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public class SpnPointSerializer implements JsonSerializer<SpnPoint> {

    @Override
    public JsonElement serialize(SpnPoint src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("city_spn_latitude", src.getSpnLatitude());
        object.addProperty("city_spn_longitude", src.getSpnLongitude());

        return object;
    }

}
