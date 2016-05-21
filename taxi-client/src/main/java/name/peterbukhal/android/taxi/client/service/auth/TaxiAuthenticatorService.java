package name.peterbukhal.android.taxi.client.service.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class TaxiAuthenticatorService extends Service {

    private TaxiAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        mAuthenticator = new TaxiAuthenticator(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }

}
