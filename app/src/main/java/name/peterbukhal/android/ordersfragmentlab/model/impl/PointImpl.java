package name.peterbukhal.android.ordersfragmentlab.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.ordersfragmentlab.model.Point;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class PointImpl implements Point {

    private Double latitude;
    private Double longitude;

    public PointImpl() {

    }

    protected PointImpl(Parcel parcel) {
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
