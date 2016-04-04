package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.Order;
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

        return order;
    }

}
