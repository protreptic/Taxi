package name.peterbukhal.android.taxi.client.service.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class TaxiGcmListenerService extends GcmListenerService {

    private static final String LOG_TAG = "TaxikGcmListener";
    public static final String ACTION_GCM_NEW_MESSAGE =
            "name.peterbukhal.android.taxi.client.service.gcm.action.ACTION_GCM_NEW_MESSAGE";
    public static final String EXTRA_GCM_MESSAGE = "extra_gcm_message";

    private final LocalBroadcastManager broadcastManager;

    public TaxiGcmListenerService() {
        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        TaxiGcmMessage message = new TaxiGcmMessage(
                data.getLong("event_id", 0),
                data.getLong("order_id", 0),
                data.getLong("city_id", 0),
                data.getString("body", ""));

        Log.d(LOG_TAG, "New gcm message received from " + from + " with body:\n'" + message + "'.");

        broadcastMessage(message);
    }

    private void broadcastMessage(TaxiGcmMessage message) {
        Intent intent = new Intent(ACTION_GCM_NEW_MESSAGE);
        intent.putExtra(EXTRA_GCM_MESSAGE, message);

        broadcastManager.sendBroadcast(intent);
    }

}
