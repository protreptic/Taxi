package name.peterbukhal.android.taxi.partner;

import android.app.Application;
import android.content.Intent;

import name.peterbukhal.android.taxi.partner.service.ntp.NtpService;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class TaxiPartner extends Application implements Thread.UncaughtExceptionHandler {

    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(getApplicationContext(), NtpService.class));
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

    }

}
