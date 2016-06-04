package name.peterbukhal.android.taxi.client.model.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.model.Point;
import name.peterbukhal.android.taxi.client.model.SpnPoint;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullCity implements City {

    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getApiUrl() {
        return "";
    }

    @Override
    public Point getPoint() {
        return new NullPoint();
    }

    @Override
    public SpnPoint getSpnPoint() {
        return new NullSpnPoint();
    }

    @Override
    public Boolean getTransfers() {
        return false;
    }

    @Override
    public String[] getInAppPayMethods() {
        return new String[0];
    }

    @Override
    public Long getExperimentalEconomPlus() {
        return 0L;
    }

    @Override
    public Boolean isNull() {
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
