package name.peterbukhal.android.taxi.client.service.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmListenerService.EXTRA_GCM_MESSAGE;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public final class TaxiGcmMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(TaxiGcmListenerService.ACTION_GCM_MESSAGE_RECEIVED)) {
            if (intent.getExtras() != null && intent.getExtras().containsKey(EXTRA_GCM_MESSAGE)) {
                TaxiGcmMessage message = intent.getExtras().getParcelable(EXTRA_GCM_MESSAGE);

                Toast.makeText(context, "New message received: " + message, Toast.LENGTH_LONG).show();
            }
        }
    }

}
