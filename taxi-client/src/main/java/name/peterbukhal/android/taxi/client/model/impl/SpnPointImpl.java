package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.SpnPoint;
import name.peterbukhal.android.taxi.client.model.mut.MutableSpnPoint;
import name.peterbukhal.android.taxi.client.model.Mutable;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class SpnPointImpl implements SpnPoint, Mutable<SpnPoint> {

    private final Double spnLatitude;
    private final Double spnLongitude;

    public SpnPointImpl(Double spnLatitude, Double spnLongitude) {
        this.spnLatitude = spnLatitude;
        this.spnLongitude = spnLongitude;
    }

    public SpnPointImpl(SpnPoint spnPoint) {
        this.spnLatitude = spnPoint.getSpnLatitude();
        this.spnLongitude = spnPoint.getSpnLongitude();
    }

    protected SpnPointImpl(Parcel in) {
        this.spnLatitude = (Double) in.readValue(Double.class.getClassLoader());
        this.spnLongitude = (Double) in.readValue(Double.class.getClassLoader());
    }

    @Override
    public Double getSpnLatitude() {
        return spnLatitude;
    }

    @Override
    public Double getSpnLongitude() {
        return spnLongitude;
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public Boolean isZero() {
        return (spnLatitude == 0.0D && spnLongitude == 0.0D);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.spnLatitude);
        dest.writeValue(this.spnLongitude);
    }

    public static final Creator<SpnPoint> CREATOR = new Creator<SpnPoint>() {

        @Override
        public SpnPoint createFromParcel(Parcel source) {
            return new SpnPointImpl(source);
        }

        @Override
        public SpnPoint[] newArray(int size) {
            return new SpnPoint[size];
        }

    };

    @Override
    public SpnPoint toMutable() {
        return new MutableSpnPoint(this);
    }

}
