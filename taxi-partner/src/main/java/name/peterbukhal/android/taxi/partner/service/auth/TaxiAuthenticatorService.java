package name.peterbukhal.android.taxi.partner.service.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
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
