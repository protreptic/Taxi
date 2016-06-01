package name.peterbukhal.android.taxi.client.service.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class GcmInstanceIdService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        startService(new Intent(this, GcmRegistrationService.class));
    }

}
