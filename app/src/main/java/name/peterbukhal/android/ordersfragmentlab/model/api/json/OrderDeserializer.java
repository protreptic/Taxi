package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import name.peterbukhal.android.ordersfragmentlab.model.Order;
import name.peterbukhal.android.ordersfragmentlab.model.RoutePoint;
import name.peterbukhal.android.ordersfragmentlab.model.impl.DeparturePoint;
import name.peterbukhal.android.ordersfragmentlab.model.impl.OrderImpl;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class OrderDeserializer implements JsonDeserializer<Order> {

    @Override
    public Order deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        OrderImpl order = new OrderImpl();
        order.setId(object.get("order_id").getAsLong());
        order.setApproximatePrice(object.get("approximate_price").getAsLong());
        order.setRoutePoints(readRoutePoints(context, object));
        order.setPhoneNumber(object.get("phone_number").getAsString());
        order.setProgressState(Order.ProgressState.valueOf(object.get("order_progress").getAsInt()));

        return order;
    }

    private List<RoutePoint> readRoutePoints(JsonDeserializationContext context, JsonObject jsonObject) {
        List<RoutePoint> routePoints = new ArrayList<>();

        JsonArray jsonArray = jsonObject.getAsJsonArray("route_points");

        if (jsonArray != null) {
            for (int count = 0; count < jsonArray.size(); count++) {
                routePoints.add(context.<RoutePoint>deserialize(jsonArray.get(count), RoutePoint.class));
            }
        }

        return routePoints;
    }

    private List<DeparturePoint> readDeparturePoints(JsonDeserializationContext context, JsonObject jsonObject) {
        List<DeparturePoint> departurePoints = new ArrayList<>();

        JsonElement jsonElement = jsonObject.get("route_points");

        if (jsonElement != null) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            for (int count = 0; count < jsonArray.size(); count++) {
                departurePoints.add(context.<DeparturePoint>deserialize(jsonArray.get(count), DeparturePoint.class));
            }
        }

        return departurePoints;
    }

}
