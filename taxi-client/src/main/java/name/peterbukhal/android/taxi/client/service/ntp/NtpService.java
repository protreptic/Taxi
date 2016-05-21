package name.peterbukhal.android.taxi.client.service.ntp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class NtpService extends Service {

    public static final String LOG_TAG = "NtpService";

    private ScheduledExecutorService mExecutorService;
    private LocalBroadcastManager mBroadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "Service started.");

        requestTime();

        return super.onStartCommand(intent, flags, startId);
    }

    private void requestTime() {
        mExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                String serverName = "ntp1.stratum1.ru";

                try {
                    // Send request
                    DatagramSocket socket = new DatagramSocket();
                    InetAddress address = InetAddress.getByName(serverName);
                    byte[] buf = new NtpMessage().toByteArray();
                    DatagramPacket packet =
                            new DatagramPacket(buf, buf.length, address, 123);

                    // Set the transmit timestamp *just* before sending the packet
                    // ToDo: Does this actually improve performance or not?
                    NtpMessage.encodeTimestamp(packet.getData(), 40,
                            (System.currentTimeMillis() / 1000.0) + 2208988800.0);

                    socket.send(packet);

                    // Get response
                    Log.d(LOG_TAG, "NTP request sent, waiting for response.");

                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    socket.close();

                    // Immediately record the incoming timestamp
                    double destinationTimestamp =
                            (System.currentTimeMillis() / 1000.0) + 2208988800.0;

                    // Process response
                    NtpMessage msg = new NtpMessage(packet.getData());

                    // Corrected, according to RFC2030 errata
                    double roundTripDelay = (destinationTimestamp - msg.originateTimestamp) -
                            (msg.transmitTimestamp - msg.receiveTimestamp);

                    double localClockOffset =
                            ((msg.receiveTimestamp - msg.originateTimestamp) +
                                    (msg.transmitTimestamp - destinationTimestamp)) / 2;

                    Log.d(LOG_TAG, "NTP server: " + serverName);
                    Log.d(LOG_TAG, msg.toString());
                    Log.d(LOG_TAG, "Dest. timestamp:     " +
                            NtpMessage.timestampToString(destinationTimestamp));
                    Log.d(LOG_TAG, "Round-trip delay: " +
                            new DecimalFormat("0.00").format(roundTripDelay * 1000) + " ms");
                    Log.d(LOG_TAG, "Local clock offset: " +
                            new DecimalFormat("0.00").format(localClockOffset * 1000) + " ms");

                    broadcastTimeUpdated(msg);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, TimeUnit.SECONDS);
    }

    public static final String ACTION_TIME_UPDATED =
            "name.peterbukhal.android.taxi.client.service.ntp.action.ACTION_TIME_UPDATED";

    public static final String EXTRA_NTP_MESSAGE = "extra_ntp_message";

    private void broadcastTimeUpdated(NtpMessage message) {
        Intent intent = new Intent(ACTION_TIME_UPDATED);
        intent.putExtra(EXTRA_NTP_MESSAGE, message);

        mBroadcastManager.sendBroadcast(intent);

        Log.d(LOG_TAG, "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mExecutorService.shutdown();

        Log.d(LOG_TAG, "Service stopped.");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
