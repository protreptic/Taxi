package name.peterbukhal.android.taxi.client.model.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Point;

/**
 * Created by
 *      petronic on 19.05.16.
 */
public final class MutablePoint implements Point {

    private Double latitude;
    private Double longitude;

    public MutablePoint() {
        this.latitude = 0.0D;
        this.longitude = 0.0D;
    }

    public MutablePoint(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MutablePoint(Point point) {
        this.latitude = point.getLatitude();
        this.longitude = point.getLongitude();
    }

    protected MutablePoint(Parcel parcel) {
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

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public Boolean isZero() {
        return (latitude == 0.0D && longitude == 0.0D);
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
            return new MutablePoint(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }

    };

}
