package name.peterbukhal.android.taxi.partner.model;

import java.util.List;

/**
 * Created by
 *      petronic on 08.06.16.
 */
public interface Track extends Nullable {
    Long getId();
    String getName();
    List<TrackPoint> getTrackPoints();
    Float getDistance();
    Float getAvgSpeed();
}
