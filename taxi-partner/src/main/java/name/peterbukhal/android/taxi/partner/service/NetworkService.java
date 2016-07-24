package name.peterbukhal.android.taxi.partner.service;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import name.peterbukhal.android.taxi.protocol.Ping;
import name.peterbukhal.android.taxi.protocol.Pong;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class NetworkService extends Service {

    private ExecutorService mExecutorService;
    private LocationManager mLocationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mExecutorService = Executors.newSingleThreadExecutor();
    }

    private final Runnable mMainThread = new Runnable() {

        private Socket mSocket;
        private InputStream mInputStream;
        private OutputStream mOutputStream;

        @Override
        public void run() {
            Log.d("PingPong", "Connecting to server...");

            try {
                mSocket = new Socket(InetAddress.getByName("192.168.1.2"), 7342);

                mInputStream = mSocket.getInputStream();
                mOutputStream = mSocket.getOutputStream();
            } catch (Exception e) {
                Log.d("PingPong", "Connection FAILED");

                reconnect();

                return;
            }

            try {
                Log.d("PingPong", "Connection OK");

                final byte[] buffer = new byte[4 * 1024];

                //noinspection InfiniteLoopStatement
                while (true) {
                    Location location = null;

                    try {
                        location = mLocationManager.getLastKnownLocation(
                                mLocationManager.getBestProvider(new Criteria(), true));
                    } catch (SecurityException e) {
                        //
                    }

                    if (location == null) {
                        location = new Location("none");
                    }

                    mOutputStream.write(
                            Ping.ADAPTER.encode(
                                    new Ping.Builder()
                                            .provider(location.getProvider())
                                            .time(location.getTime())
                                            .latitude((float) location.getLatitude())
                                            .longitude((float) location.getLongitude())
                                            .accuracy((int) location.getAccuracy())
                                            .bearing((int) location.getBearing())
                                            .speed((int) location.getSpeed())
                                            .build()));

                    Pong pong = Pong.ADAPTER.decode(
                            Arrays.copyOf(buffer, mInputStream.read(buffer)));

                    Log.d("PingPong", pong.toString());

                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (Exception e) {
                Log.d("PingPong", "Server disconnected.");

                reconnect();
            } finally {
                if (mSocket != null) {
                    try {
                        mSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void reconnect() {
            Log.d("PingPong", "Wait for 10 seconds to reconnect ...");

            try {
                TimeUnit.SECONDS.sleep(10);

                mExecutorService.submit(mMainThread);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mExecutorService.submit(mMainThread);

        return 0;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mExecutorService.shutdownNow();
    }
}
