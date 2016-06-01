package name.peterbukhal.android.taxi.client.service.gcm;

import android.accounts.Account;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class GcmRegistrationService extends IntentService {

    public static final String LOG_TAG = "GcmRegistration";
    public static final String ACTION_GCM_REGISTRATION =
            "name.peterbukhal.android.taxi.client.service.gcm.action.ACTION_GCM_REGISTRATION";
    public static final String EXTRA_GCM_TOKEN = "extra_gcm_token";

    private final LocalBroadcastManager mBroadcastManager;
    private final TaxiAccountManager mAccountManager;

    public GcmRegistrationService() {
        super(LOG_TAG);

        mBroadcastManager = LocalBroadcastManager.getInstance(getBaseContext());
        mAccountManager = TaxiAccountManager.get(getBaseContext());
    }

    @Override
    public void onHandleIntent(Intent intent) {
        if (intent.getExtras() != null && intent.getExtras().containsKey(TaxiAccountManager.EXTRA_ACCOUNT)) {
            Account account = intent.getParcelableExtra(TaxiAccountManager.EXTRA_ACCOUNT);

            InstanceID instanceId = InstanceID.getInstance(getBaseContext());

            try {
                String gcmToken = instanceId.getToken(getString(R.string.server_sender_id),
                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                mAccountManager.setGcmToken(account, gcmToken);

                Log.d(LOG_TAG, "New GCM token obtained:\n" + gcmToken);

                Intent tokenIntent = new Intent(ACTION_GCM_REGISTRATION);
                tokenIntent.putExtra(EXTRA_GCM_TOKEN, gcmToken);

                mBroadcastManager.sendBroadcast(tokenIntent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
