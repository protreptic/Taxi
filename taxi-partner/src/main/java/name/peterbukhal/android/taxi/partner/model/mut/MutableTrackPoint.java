package name.peterbukhal.android.taxi.partner.model.mut;

import name.peterbukhal.android.taxi.partner.model.Immutable;
import name.peterbukhal.android.taxi.partner.model.TrackPoint;
import name.peterbukhal.android.taxi.partner.model.impl.TrackPointImpl;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class MutableTrackPoint implements TrackPoint, Immutable<TrackPoint> {

    private final Long trackId;
    private final String provider;
    private final Float latitude;
    private final Float longitude;
    private final Float speed;
    private final Float accuracy;

    public MutableTrackPoint(Long trackId, String provider, Float accuracy, Float latitude,
                             Float longitude, Float speed) {
        this.trackId = trackId;
        this.accuracy = accuracy;
        this.provider = provider;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }

    public MutableTrackPoint(TrackPoint trackPoint) {
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
    public TrackPoint toImmutable() {
        return new TrackPointImpl(this);
    }

}
