package name.peterbukhal.android.ordersfragmentlab.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.ordersfragmentlab.model.Point;
import name.peterbukhal.android.ordersfragmentlab.model.RoutePoint;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class RoutePointImpl implements RoutePoint {

    private Long type;
    private String name;
    private String address;
    private Point point;

    public RoutePointImpl() {

    }

    @Override
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    protected RoutePointImpl(Parcel parcel) {
        type = parcel.readLong();
        name = parcel.readString();
        address = parcel.readString();
        point = parcel.readParcelable(Point.class.getClassLoader());
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
