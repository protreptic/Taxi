package name.peterbukhal.android.taxi.partner.model.impl;

import android.location.Location;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.partner.model.Mutable;
import name.peterbukhal.android.taxi.partner.model.Track;
import name.peterbukhal.android.taxi.partner.model.TrackPoint;
import name.peterbukhal.android.taxi.partner.model.mut.MutableTrack;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class TrackImpl implements Track, Mutable<Track> {

    private final Long id;
    private final String name;
    private final List<TrackPoint> trackPoints = Collections.emptyList();

    public TrackImpl(Long id, String name) {
        this.id = id;
        this.name = name;
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
        return Collections.unmodifiableList(trackPoints);
    }

    @Override
    public Float getDistance() {
        if (trackPoints.size() < 2) return 0.0F;

        float[] results = new float[1];
        float distance = 0.0F;

        for (int position = 1; position < trackPoints.size(); position++) {
            TrackPoint point1 = trackPoints.get(position - 1);
            TrackPoint point2 = trackPoints.get(position);

            Location.distanceBetween(
                    point2.getLatitude(), point2.getLongitude(),
                    point1.getLatitude(), point1.getLongitude(), results);

            distance += results[0];
        }

        return distance / 1000.0F;
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
    public MutableTrack toMutable() {
        return new MutableTrack();
    }

}
