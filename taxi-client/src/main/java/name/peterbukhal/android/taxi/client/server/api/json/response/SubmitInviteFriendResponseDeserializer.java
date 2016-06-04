package name.peterbukhal.android.taxi.client.server.api.json.response;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by
 *      petronic on 03.06.16.
 */
public final class SubmitInviteFriendResponseDeserializer implements JsonDeserializer<SubmitInviteFriendResponse> {

    @Override
    public SubmitInviteFriendResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new SubmitInviteFriendResponse(json.getAsJsonObject().get("status").getAsBoolean());
    }

}
