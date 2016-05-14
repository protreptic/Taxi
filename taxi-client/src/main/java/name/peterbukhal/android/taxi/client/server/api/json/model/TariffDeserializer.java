package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.Tariff;
import name.peterbukhal.android.taxi.client.model.impl.TariffImpl;

/**
 * Created by
 * petronic on 25.03.16.
 */
public class TariffDeserializer implements JsonDeserializer<Tariff> {

    @Override
    public Tariff deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        return new TariffImpl(
                object.get("tariff_id").getAsLong(),
                object.get("tariff_name").getAsString(),
                object.get("tariff_rate").getAsLong(),
                object.get("tariff_order").getAsLong());
    }
}
