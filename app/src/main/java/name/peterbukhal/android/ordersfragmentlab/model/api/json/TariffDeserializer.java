package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.ordersfragmentlab.model.Tariff;
import name.peterbukhal.android.ordersfragmentlab.model.impl.TariffImpl;

/**
 * Created by
 * petronic on 25.03.16.
 */
public class TariffDeserializer implements JsonDeserializer<Tariff> {

    @Override
    public Tariff deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        TariffImpl tariff = new TariffImpl();
        tariff.setId(object.get("tariff_id").getAsLong());
        tariff.setName(object.get("tariff_name").getAsString());
        tariff.setRate(object.get("tariff_rate").getAsLong());
        tariff.setOrder(object.get("tariff_order").getAsLong());

        return tariff;
    }
}
