package name.peterbukhal.android.ordersfragmentlab.service.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class TaxikGcmInstanceIdListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        startService(new Intent(this, TaxikGcmRegistrationIntentService.class));
    }

}
