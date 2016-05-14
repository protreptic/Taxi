package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.Conditions;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class ConditionsSerializer implements JsonSerializer<Conditions> {

    @Override
    public JsonElement serialize(Conditions src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("car_category", src.getCarCategory());
        object.addProperty("conditioner", src.getConditioner());
        object.addProperty("smoking", src.getSmoking());
        object.addProperty("baggage", src.getBaggage());
        object.addProperty("animals", src.getAnimals());
        object.addProperty("universal", src.getUniversal());
        object.addProperty("child_sit", src.getChildSit());
        object.addProperty("payment_slip", src.getPaymentSlip());
        object.addProperty( "credit_card", src.getCreditCard());
        object.addProperty("bonus_pay", src.getBonusPay());
        object.addProperty("yellow_numbers", src.getYellowNumbers());

        return object;
    }

}
