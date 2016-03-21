package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import name.peterbukhal.android.ordersfragmentlab.model.Order;
import name.peterbukhal.android.ordersfragmentlab.model.Orders;

/**
 * Created by
 * petronic on 21.03.16.
 */
public class OrdersDeserializer implements JsonDeserializer<Orders> {

    @Override
    public Orders deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        List<Order> orders = new ArrayList<>();

        for (int count = 0; count < object.getAsJsonArray("orders").size(); count++) {
            orders.add(context.<Order>deserialize(object.getAsJsonArray("orders").get(count), Order.class));
        }

        return new Orders(orders, object.get("total_count").getAsLong());
    }

}
