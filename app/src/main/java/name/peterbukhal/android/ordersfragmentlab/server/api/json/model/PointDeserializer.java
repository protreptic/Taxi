package name.peterbukhal.android.ordersfragmentlab.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.Point;
import name.peterbukhal.android.ordersfragmentlab.model.impl.PointImpl;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class PointDeserializer implements JsonDeserializer<Point> {

    @Override
    public Point deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        PointImpl routePoint = new PointImpl();
        routePoint.setLatitude(object.get("latitude").getAsDouble());
        routePoint.setLongitude(object.get("longitude").getAsDouble());

        return routePoint;
    }

}
