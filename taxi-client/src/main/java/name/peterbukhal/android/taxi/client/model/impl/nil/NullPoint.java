package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Point;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullPoint implements Point {

    @Override
    public Double getLatitude() {
        return 0.0D;
    }

    @Override
    public Double getLongitude() {
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
