package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.Conditions;
import name.peterbukhal.android.taxi.client.model.impl.ConditionsImpl;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class ConditionsDeserializer implements JsonDeserializer<Conditions> {

    @Override
    public Conditions deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        return new ConditionsImpl(
                object.get("car_category").getAsLong(),
                object.get("conditioner").getAsBoolean(),
                object.get("smoking").getAsLong(),
                object.get("baggage").getAsBoolean(),
                object.get("animals").getAsBoolean(),
                object.get("universal").getAsBoolean(),
                object.get("child_sit").getAsLong(),
                object.get("payment_slip").getAsBoolean(),
                object.get("credit_card").getAsBoolean(),
                object.get("bonus_pay").getAsBoolean(),
                object.get("yellow_numbers").getAsBoolean(),
                object.get("inapp_pay").getAsBoolean());
    }

}
