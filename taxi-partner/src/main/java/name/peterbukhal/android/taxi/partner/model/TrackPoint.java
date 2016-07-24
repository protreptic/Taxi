package name.peterbukhal.android.taxi.partner.model;

import name.peterbukhal.android.taxi.partner.model.nil.NullTrackPoint;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public interface TrackPoint extends Nullable {
    Long getTrackId();
    String getProvider();
    Float getLatitude();
    Float getLongitude();
    Float getSpeed();
    Float getAccuracy();

    TrackPoint NULL = new NullTrackPoint();
}
