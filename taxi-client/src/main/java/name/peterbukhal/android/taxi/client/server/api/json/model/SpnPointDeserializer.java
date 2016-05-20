package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.SpnPoint;
import name.peterbukhal.android.taxi.client.model.impl.SpnPointImpl;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public class SpnPointDeserializer implements JsonDeserializer<SpnPoint> {

    @Override
    public SpnPoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        return new SpnPointImpl(
                object.get("city_spn_latitude").getAsDouble(),
                object.get("city_spn_longitude").getAsDouble());
    }

}
