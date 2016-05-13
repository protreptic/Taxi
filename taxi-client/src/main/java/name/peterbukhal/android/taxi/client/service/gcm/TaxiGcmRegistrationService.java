package name.peterbukhal.android.taxi.client.service.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import name.peterbukhal.android.taxi.client.R;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class TaxiGcmRegistrationService extends IntentService {

    public static final String LOG_TAG = "GcmRegistration";
    public static final String ACTION_GCM_REGISTRATION =
            "name.peterbukhal.android.taxi.client.service.gcm.action.ACTION_GCM_REGISTRATION";
    public static final String EXTRA_GCM_TOKEN = "extra_gcm_token";

    private LocalBroadcastManager broadcastManager;

    public TaxiGcmRegistrationService() {
        super(LOG_TAG);

        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    public void onHandleIntent(Intent intent) {
        InstanceID instanceId = InstanceID.getInstance(getApplicationContext());

        try {
            String gcmToken = instanceId.getToken(getString(R.string.server_sender_id), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Intent tokenIntent = new Intent(ACTION_GCM_REGISTRATION);
            tokenIntent.putExtra(EXTRA_GCM_TOKEN, gcmToken);

            broadcastManager.sendBroadcast(tokenIntent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
