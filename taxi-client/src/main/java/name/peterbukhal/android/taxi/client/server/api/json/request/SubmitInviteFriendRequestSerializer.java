package name.peterbukhal.android.taxi.client.server.api.json.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 03.06.16.
 */
public final class SubmitInviteFriendRequestSerializer implements JsonSerializer<SubmitInviteFriendRequest> {

    @Override
    public JsonElement serialize(SubmitInviteFriendRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("api_version", src.getApiVersion());
        jsonObject.addProperty("token", src.getToken());
        jsonObject.addProperty("phone_number", src.getPhoneNumber());

        return jsonObject;
    }

}
