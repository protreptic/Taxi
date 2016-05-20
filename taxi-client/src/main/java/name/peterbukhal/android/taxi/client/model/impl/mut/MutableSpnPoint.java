package name.peterbukhal.android.taxi.client.model.impl.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.SpnPoint;
import name.peterbukhal.android.taxi.client.model.impl.SpnPointImpl;
import name.peterbukhal.android.taxi.client.unused.Immutable;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class MutableSpnPoint implements SpnPoint, Immutable<SpnPoint> {

    private Double spnLatitude;
    private Double spnLongitude;

    public MutableSpnPoint() {
        this.spnLatitude = 0.0D;
        this.spnLongitude = 0.0D;
    }

    public MutableSpnPoint(Double spnLatitude, Double spnLongitude) {
        this.spnLatitude = spnLatitude;
        this.spnLongitude = spnLongitude;
    }

    public MutableSpnPoint(SpnPoint spnPoint) {
        this.spnLatitude = spnPoint.getSpnLatitude();
        this.spnLongitude = spnPoint.getSpnLongitude();
    }

    protected MutableSpnPoint(Parcel in) {
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
        public SpnPoint createFromParcel(Parcel in) {
            return new MutableSpnPoint(in);
        }

        @Override
        public SpnPoint[] newArray(int size) {
            return new SpnPoint[size];
        }

    };

    @Override
    public SpnPoint toImmutable() {
        return new SpnPointImpl(this);
    }

}
