package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.Point;
import name.peterbukhal.android.taxi.client.model.RoutePoint;
import name.peterbukhal.android.taxi.client.model.nil.NullPoint;
import name.peterbukhal.android.taxi.client.model.impl.RoutePointImpl;

/**
 * Created by
 *      petronic on 07.05.16.
 */
public class RoutePointDeserializer implements JsonDeserializer<RoutePoint> {

    @Override
    public RoutePoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Point point = object.getAsJsonObject("point") != null ?
                context.<Point>deserialize(
                        object.getAsJsonObject("point"), Point.class) : new NullPoint();

        return new RoutePointImpl(
                object.get("type").getAsLong(),
                object.get("name").getAsString(),
                object.get("address").getAsString(),
                point);
    }

}
