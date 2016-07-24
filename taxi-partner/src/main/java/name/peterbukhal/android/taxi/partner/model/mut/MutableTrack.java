package name.peterbukhal.android.taxi.partner.model.mut;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.partner.model.Immutable;
import name.peterbukhal.android.taxi.partner.model.Track;
import name.peterbukhal.android.taxi.partner.model.TrackPoint;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class MutableTrack implements Track, Immutable<TrackPoint> {

    private Long id;
    private String name;
    private List<TrackPoint> trackPoints;

    public MutableTrack() {
        this.id = 0L;
        this.name = "";
        this.trackPoints = Collections.emptyList();
    }

    public MutableTrack(Long id, String name, List<TrackPoint> trackPoints) {
        this.id = id;
        this.name = name;
        this.trackPoints = Collections.unmodifiableList(trackPoints);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<TrackPoint> getTrackPoints() {
        return Collections.emptyList();
    }

    @Override
    public Float getDistance() {
        return 0.0F;
    }

    @Override
    public Float getAvgSpeed() {
        return 0.0F;
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public TrackPoint toImmutable() {
        return TrackPoint.NULL;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = Collections.unmodifiableList(trackPoints);
    }

}
