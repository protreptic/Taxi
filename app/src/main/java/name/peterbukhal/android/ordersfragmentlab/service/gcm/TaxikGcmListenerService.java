package name.peterbukhal.android.ordersfragmentlab.service.gcm;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;

import java.lang.reflect.Type;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class TaxikGcmListenerService extends GcmListenerService {

    private static final String LOG_TAG = "TaxikGcmListener";

    public static final String ACTION_GCM_NEW_MESSAGE =
            "name.peterbukhal.android.ordersfragmentlab.service.gcm.action.ACTION_GCM_NEW_MESSAGE";
    public static final String EXTRA_GCM_MESSAGE = "extra_gcm_message";

    private Gson gson;

    private static class TaxikGcmMessageSerializer implements JsonSerializer<TaxikGcmMessage> {

        @Override
        public JsonElement serialize(TaxikGcmMessage src, Type typeOfSrc, JsonSerializationContext context) {
            return null;
        }

    }

    private static class TaxikGcmMessageDeserializer implements JsonDeserializer<TaxikGcmMessage> {

        @Override
        public TaxikGcmMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return null;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();

        gson = new GsonBuilder()
                .registerTypeAdapter(TaxikGcmMessage.class, new TaxikGcmMessageSerializer())
                .registerTypeAdapter(TaxikGcmMessage.class, new TaxikGcmMessageDeserializer())
                .serializeNulls()
                .create();
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        TaxikGcmMessage message = gson.fromJson("", TaxikGcmMessage.class);

        Log.d(LOG_TAG, "New message received from " + from + " with body:\n'" + message + "'.");
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String msgId) {
        super.onMessageSent(msgId);
    }

    @Override
    public void onSendError(String msgId, String error) {
        super.onSendError(msgId, error);
    }

}
