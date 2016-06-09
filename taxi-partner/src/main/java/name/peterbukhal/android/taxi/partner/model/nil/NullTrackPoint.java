package name.peterbukhal.android.taxi.partner.model.nil;

import name.peterbukhal.android.taxi.partner.model.TrackPoint;

/**
 * Created by
 *      petronic on 08.06.16.
 */
public final class NullTrackPoint implements TrackPoint {

    @Override
    public Long getTrackId() {
        return 0L;
    }

    @Override
    public String getProvider() {
        return "";
    }

    @Override
    public Float getLatitude() {
        return 0.0F;
    }

    @Override
    public Float getLongitude() {
        return 0.0F;
    }

    @Override
    public Float getSpeed() {
        return 0.0F;
    }

    @Override
    public Float getAccuracy() {
        return 0.0F;
    }

    @Override
    public Boolean isNull() {
        return true;
    }

}
