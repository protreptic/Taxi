package name.peterbukhal.android.taxi.partner.model;

import java.util.List;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public interface Track extends Nullable {
    Long getId();
    String getName();
    List<TrackPoint> getTrackPoints();
    Float getDistance();
    Float getAvgSpeed();
}
