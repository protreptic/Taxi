package name.peterbukhal.android.taxi.partner.model.impl;

import android.location.Location;

import java.util.Locale;

import name.peterbukhal.android.taxi.partner.model.Mutable;
import name.peterbukhal.android.taxi.partner.model.TrackPoint;
import name.peterbukhal.android.taxi.partner.model.mut.MutableTrackPoint;

/**
 * Created by
 *      petronic on 08.06.16.
 */
public final class TrackPointImpl implements TrackPoint, Mutable<TrackPoint> {

    private final Long trackId;
    private final String provider;
    private final Float latitude;
    private final Float longitude;
    private final Float speed;
    private final Float accuracy;

    public TrackPointImpl(Long trackId, String provider, Float latitude,
                          Float longitude, Float speed, Float accuracy) {
        this.trackId = trackId;
        this.accuracy = accuracy;
        this.provider = provider;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }

    public TrackPointImpl(TrackPoint trackPoint) {
        this.trackId = trackPoint.getTrackId();
        this.accuracy = trackPoint.getAccuracy();
        this.provider = trackPoint.getProvider();
        this.latitude = trackPoint.getLatitude();
        this.longitude = trackPoint.getLongitude();
        this.speed = trackPoint.getSpeed();
    }

    @Override
    public Long getTrackId() {
        return trackId;
    }

    @Override
    public Float getAccuracy() {
        return accuracy;
    }

    @Override
    public Float getLatitude() {
        return latitude;
    }

    @Override
    public Float getLongitude() {
        return longitude;
    }

    @Override
    public String getProvider() {
        return provider;
    }

    @Override
    public Float getSpeed() {
        return speed;
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public MutableTrackPoint toMutable() {
        return new MutableTrackPoint(this);
    }

    @Override
    public String toString() {
        return String.format(
                Locale.US,
                "%s %.6f %.6f (%.2f km/h) %.0fm",
                getProvider(),
                getLatitude(),
                getLongitude(),
                getSpeed(),
                getAccuracy());
    }

    public static TrackPoint fromLocation(Long trackId, Location location) {
        return new TrackPointImpl(
                trackId,
                location.getProvider().substring(0, 1),
                (float) location.getLatitude(),
                (float) location.getLongitude(),
                location.getSpeed(),
                location.getAccuracy());
    }

}
