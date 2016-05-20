package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.SpnPoint;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class NullSpnPoint implements SpnPoint {

    @Override
    public Double getSpnLatitude() {
        return 0.0D;
    }

    @Override
    public Double getSpnLongitude() {
        return 0.0D;
    }

    @Override
    public Boolean isNull() {
        return true;
    }

    @Override
    public Boolean isZero() {
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
