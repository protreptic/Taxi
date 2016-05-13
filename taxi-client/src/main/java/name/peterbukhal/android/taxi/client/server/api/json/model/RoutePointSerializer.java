package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.RoutePoint;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class RoutePointSerializer implements JsonSerializer<RoutePoint> {

    @Override
    public JsonElement serialize(RoutePoint src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

}
