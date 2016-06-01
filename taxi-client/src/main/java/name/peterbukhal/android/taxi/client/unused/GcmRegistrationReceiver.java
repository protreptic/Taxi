package name.peterbukhal.android.taxi.client.unused;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import name.peterbukhal.android.taxi.client.service.gcm.GcmRegistrationService;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public final class GcmRegistrationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(GcmRegistrationService.ACTION_GCM_REGISTRATION)) {
            @SuppressWarnings("unused")
            String gcmToken =
                    intent.getStringExtra(GcmRegistrationService.EXTRA_GCM_TOKEN);

            Toast.makeText(context, "New GCM token obtained", Toast.LENGTH_SHORT).show();
        }
    }

}
