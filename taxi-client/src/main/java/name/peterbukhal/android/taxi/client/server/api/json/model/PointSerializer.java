package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.Point;

/**
 * Created by
 * petronic on 09.05.16.
 */
public class PointSerializer implements JsonSerializer<Point> {

    @Override
    public JsonElement serialize(Point src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("latitude", src.getLatitude());
        object.addProperty("longitude", src.getLongitude());

        return object;
    }

}
