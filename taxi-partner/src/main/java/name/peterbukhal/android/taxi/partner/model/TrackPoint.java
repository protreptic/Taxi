package name.peterbukhal.android.taxi.partner.model;

import name.peterbukhal.android.taxi.partner.model.nil.NullTrackPoint;

/**
 * Created by
 *      petronic on 08.06.16.
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
