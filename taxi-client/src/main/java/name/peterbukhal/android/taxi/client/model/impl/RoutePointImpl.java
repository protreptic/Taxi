package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Point;
import name.peterbukhal.android.taxi.client.model.RoutePoint;

/**
 * Created by
 *      petronic on 07.05.16.
 */
public final class RoutePointImpl implements RoutePoint {

    private final Long type;
    private final String name;
    private final String address;
    private final Point point;

    public RoutePointImpl(Long type, String name, String address, Point point) {
        this.type = type;
        this.name = name;
        this.address = address;
        this.point = point;
    }

    public RoutePointImpl(RoutePoint routePoint) {
        this.type = routePoint.getType();
        this.name = routePoint.getName();
        this.address = routePoint.getAddress();
        this.point = routePoint.getPoint();
    }

    protected RoutePointImpl(Parcel parcel) {
        type = parcel.readLong();
        name = parcel.readString();
        address = parcel.readString();
        point = parcel.readParcelable(Point.class.getClassLoader());
    }

    @Override
    public Long getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(type);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeParcelable(point, 0);
    }

    public static final Creator<RoutePoint> CREATOR = new Creator<RoutePoint>() {

        @Override
        public RoutePoint createFromParcel(Parcel in) {
            return new RoutePointImpl(in);
        }

        @Override
        public RoutePoint[] newArray(int size) {
            return new RoutePoint[size];
        }

    };

}
