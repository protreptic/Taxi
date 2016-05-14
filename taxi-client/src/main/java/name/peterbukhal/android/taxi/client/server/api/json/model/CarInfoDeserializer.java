package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.CarInfo;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class CarInfoDeserializer implements JsonDeserializer<CarInfo> {

    @Override
    public CarInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

}
