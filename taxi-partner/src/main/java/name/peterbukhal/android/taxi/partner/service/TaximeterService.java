package name.peterbukhal.android.taxi.partner.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import name.peterbukhal.android.taxi.partner.R;
import name.peterbukhal.android.taxi.partner.storage.SqliteDatabaseHelper;
import name.peterbukhal.android.taxi.partner.model.impl.TrackPointImpl;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class TaximeterService extends Service {

    public static final String LOG_TAG = "Taximeter";

    private LocationManager mLocationManager;

    private LocationListener mGpsLocationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            proceedLocation(location);

            Log.d(LOG_TAG, formatLocation(location));
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {
            Log.d(LOG_TAG, provider + " provider enabled.");
        }

        public void onProviderDisabled(String provider) {
            Log.d(LOG_TAG, provider + " provider disabled.");
        }

    };

    private LocationListener mNetworkLocationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            proceedLocation(location);

            Log.d(LOG_TAG, formatLocation(location));
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {
            Log.d(LOG_TAG, provider + " provider enabled.");
        }

        public void onProviderDisabled(String provider) {
            Log.d(LOG_TAG, provider + " provider disabled.");
        }

    };

    @SuppressWarnings("unused")
    private LocationListener mPassiveLocationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            proceedLocation(location);

            Log.d(LOG_TAG, formatLocation(location));
       }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {
            Log.d(LOG_TAG, provider + " provider enabled.");
        }

        public void onProviderDisabled(String provider) {
            Log.d(LOG_TAG, provider + " provider disabled.");
        }

    };

    @SuppressWarnings("unused")
    private LocationListener mFusedLocationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            proceedLocation(location);

            Log.d(LOG_TAG, formatLocation(location));
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {
            Log.d(LOG_TAG, provider + " provider enabled.");
        }

        public void onProviderDisabled(String provider) {
            Log.d(LOG_TAG, provider + " provider disabled.");
        }

    };

    private void proceedLocation(Location location) {
        if (location == null) return;

        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
            synchronized (mGpsTrack) {
                mGpsTrack.add(location);
            }
        } else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
            synchronized (mNetworkTrack) {
                mNetworkTrack.add(location);
            }
        }

        mDatabaseHelper.addTrackPoint(TrackPointImpl.fromLocation(trackId, location));

        synchronized (mBestTrack) {
            mBestTrack.offer(location);
        }
    }

    private final Long trackId = System.currentTimeMillis();

    private String formatLocation(Location location) {
        return String.format(
                Locale.US,
                "%s %.6f %.6f (%.2f km/h) %.0fm",
                location.getProvider().substring(0, 1),
                location.getLatitude(),
                location.getLongitude(),
                transform(location.getSpeed()),
                location.getAccuracy());
    }

    private final List<Location> mGpsTrack = new ArrayList<>();
    private final List<Location> mNetworkTrack = new ArrayList<>();

    @Deprecated
    private float calculateDistance(List<Location> track) {
        if (track == null || track.size() < 2) return 0.0F;

        float[] results = new float[1];
        float distance = 0.0F;

        for (int position = 1; position < track.size(); position++) {
            Location newLocation = track.get(position - 1);
            Location oldLocation = track.get(position);

            Location.distanceBetween(
                    oldLocation.getLatitude(), oldLocation.getLongitude(),
                    newLocation.getLatitude(), newLocation.getLongitude(), results);

            distance += results[0];
        }

        return distance / 1000.0F;
    }

    private float calculateAvgSpeed(final List<Location> track, int points) {
        if (track == null || track.isEmpty() || track.size() < points) return 0.0F;

        float avgSpeed = 0.0F;

        for (int position = track.size() - 1; position >= (track.size() - points); position--) {
            avgSpeed += track.get(position).getSpeed();
        }

        return transform(avgSpeed / points);
    }

    @SuppressWarnings("unused")
    private float calculateAvgSpeed(List<Location> track) {
        return calculateAvgSpeed(track, track.size());
    }

    private static final Comparator<Location> BEST_ACCURACY_COMPARATOR = new Comparator<Location>() {

        @Override
        public int compare(Location lhs, Location rhs) {
            if (lhs.getTime() > rhs.getTime()) return -1;
            if (lhs.getAccuracy() < rhs.getAccuracy()) return -1;
            if (lhs.getAccuracy() == rhs.getAccuracy()) return 0;
            if (lhs.getAccuracy() > rhs.getAccuracy()) return 1;

            throw new RuntimeException();
        }

    };

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final PriorityQueue<Location> mBestTrack = new PriorityQueue<>(500, BEST_ACCURACY_COMPARATOR);

    /**
     * Преобразует метры в секунду в километры в час.
     *
     * @param speed скорость в метрах в секунду
     * @return скорость в километрах в час
     */
    private float transform(float speed) {
        return (speed * 3.6F);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    private static final long UPDATE_INTERVAL = 5000;

    private void updateNotification() {
        float gpsDistance = calculateDistance(Collections.unmodifiableList(mGpsTrack));
        float networkDistance = calculateDistance(Collections.unmodifiableList(mNetworkTrack));
        float avgSpeed = calculateAvgSpeed(Collections.unmodifiableList(mGpsTrack), 3);
        float price = gpsDistance * 19.0F;

        String message = "";
        message += (gpsDistance == 0.0F) ?
                "Waiting" : String.format(Locale.US, "Moving %.2f km", gpsDistance);
        message += (price == 0.0F) ?
                "" : String.format(Locale.US, " (%.2f rub.)", price);
        message += (avgSpeed == 0.0F) ?
                "" : String.format(Locale.US, " (%.2f km/h)", avgSpeed);

        Notification notification =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_directions_car_white_48dp)
                        .setContentTitle("Taximeter")
                        .setContentText(message)
                        .build();

        startForeground(2154, notification);

        Log.d(LOG_TAG, String.format(Locale.US, "gps distance %.2f km", gpsDistance));
        Log.d(LOG_TAG, String.format(Locale.US, "net distance %.2f km", networkDistance));

        broadcastData();
    }

    private LocalBroadcastManager mBroadcastManager;

    public static final String ACTION_DATA =
            "name.peterbukhal.android.taxi.partner.taximeter.action.ACTION_DATA";
    public static final String EXTRA_GPS_TRACK = "extra_gps_track";
    public static final String EXTRA_NETWORK_TRACK = "extra_network_track";

    private void broadcastData() {
        if (mGpsTrack.isEmpty() || mGpsTrack.size() < 2) return;
        if (mNetworkTrack.isEmpty() || mNetworkTrack.size() < 2) return;

        mBroadcastManager.sendBroadcast(
                new Intent(ACTION_DATA)
                        .putExtra(EXTRA_GPS_TRACK, new ArrayList<>(mGpsTrack))
                        .putExtra(EXTRA_NETWORK_TRACK, new ArrayList<>(mNetworkTrack)));
    }

    private ScheduledExecutorService mExecutorService = Executors.newScheduledThreadPool(1);
    private SqliteDatabaseHelper mDatabaseHelper;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "Service started.");

        mDatabaseHelper = new SqliteDatabaseHelper(getApplicationContext());

        try {
            requestGpsUpdates(UPDATE_INTERVAL);
            requestNetworkUpdates(UPDATE_INTERVAL);
        } catch (SecurityException e) {
            //
        }

        mExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                updateNotification();
            }
        }, 0, 15, TimeUnit.SECONDS);

        return super.onStartCommand(intent, flags, startId);
    }

    private void requestGpsUpdates(long updateInterval) {
        try {
            mLocationManager.removeUpdates(mGpsLocationListener);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, updateInterval, 0, mGpsLocationListener);
        } catch (SecurityException e) {
            //
        }
    }

    private void requestNetworkUpdates(long updateInterval) {
        try {
            mLocationManager.removeUpdates(mNetworkLocationListener);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, updateInterval, 0, mNetworkLocationListener);
        } catch (SecurityException e) {
            //
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mDatabaseHelper.close();

        try {
            mLocationManager.removeUpdates(mGpsLocationListener);
            mLocationManager.removeUpdates(mNetworkLocationListener);
        } catch (SecurityException e) {
            //
        }

        Log.d(LOG_TAG, String.format(Locale.US, "gps distance %.2f km", calculateDistance(mGpsTrack)));
        Log.d(LOG_TAG, String.format(Locale.US, "net distance %.2f km", calculateDistance(mNetworkTrack)));

        mExecutorService.shutdown();

        stopForeground(true);

        Log.d(LOG_TAG, "Service stopped.");
    }

}
