package name.peterbukhal.android.taxi.partner.service.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class GcmInstanceIdService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        startService(new Intent(getApplicationContext(), GcmRegistrationService.class));
    }

}
