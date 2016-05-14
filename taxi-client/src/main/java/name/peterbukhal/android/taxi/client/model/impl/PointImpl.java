package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Point;

/**
 * Created by
 * petronic on 07.05.16.
 */
public final class PointImpl implements Point {

    private final Double latitude;
    private final Double longitude;

    public PointImpl(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected PointImpl(Parcel parcel) {
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {

        @Override
        public Point createFromParcel(Parcel in) {
            return new PointImpl(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }

    };

}
