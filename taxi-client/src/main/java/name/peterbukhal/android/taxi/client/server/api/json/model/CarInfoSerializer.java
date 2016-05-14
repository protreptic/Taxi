package name.peterbukhal.android.taxi.client.server.api.json.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import name.peterbukhal.android.taxi.client.model.CarInfo;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class CarInfoSerializer implements JsonSerializer<CarInfo> {

    @Override
    public JsonElement serialize(CarInfo src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

}
