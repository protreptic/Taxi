package name.peterbukhal.android.ordersfragmentlab.service.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class TaxikAuthenticatorService extends Service {

    private TaxikAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        mAuthenticator = new TaxikAuthenticator(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
