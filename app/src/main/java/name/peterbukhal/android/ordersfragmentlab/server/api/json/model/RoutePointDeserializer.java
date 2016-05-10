package name.peterbukhal.android.ordersfragmentlab.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.Point;
import name.peterbukhal.android.ordersfragmentlab.model.RoutePoint;
import name.peterbukhal.android.ordersfragmentlab.model.impl.RoutePointImpl;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class RoutePointDeserializer implements JsonDeserializer<RoutePoint> {

    @Override
    public RoutePoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        RoutePointImpl routePoint = new RoutePointImpl();
        routePoint.setType(object.get("type").getAsLong());
        routePoint.setName(object.get("name").getAsString());
        routePoint.setAddress(object.get("address").getAsString());
        routePoint.setPoint(context.<Point>deserialize(object.getAsJsonObject("point"), Point.class));

        return routePoint;
    }

}
