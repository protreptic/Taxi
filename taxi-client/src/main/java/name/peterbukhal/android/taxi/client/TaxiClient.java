package name.peterbukhal.android.taxi.client;

import android.app.Application;

/**
 * Created by
 *      petronic on 22.03.16.
 */
public final class TaxiClient extends Application implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

    }

}
