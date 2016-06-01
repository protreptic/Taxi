package name.peterbukhal.android.taxi.client.service.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class GcmMessageService extends GcmListenerService {

    public static final String LOG_TAG = "TaxikGcmListener";
    public static final String ACTION_GCM_MESSAGE_RECEIVED =
            "name.peterbukhal.android.taxi.client.service.gcm.action.ACTION_GCM_MESSAGE_RECEIVED";
    public static final String EXTRA_GCM_MESSAGE = "extra_gcm_message";

    private final LocalBroadcastManager broadcastManager;

    public GcmMessageService() {
        broadcastManager = LocalBroadcastManager.getInstance(getBaseContext());
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        GcmMessage message = new GcmMessage(
                Long.valueOf(data.getString("event_id", "0")),
                Long.valueOf(data.getString("order_id", "0")),
                Long.valueOf(data.getString("city_id", "0")),
                data.getString("body", ""));

        Log.d(LOG_TAG, "New gcm message received from " + from + " with body:\n'" + message + "'.");

        broadcastMessage(message);
    }

    private void broadcastMessage(GcmMessage message) {
        Intent intent = new Intent(ACTION_GCM_MESSAGE_RECEIVED);
        intent.putExtra(EXTRA_GCM_MESSAGE, message);

        broadcastManager.sendBroadcast(intent);
    }

}
