package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CarInfo;
import name.peterbukhal.android.taxi.client.model.DriverInfo;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullDriverInfo implements DriverInfo {

    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Float getRating() {
        return 0F;
    }

    @Override
    public Long getRatingCount() {
        return 0L;
    }

    @Override
    public String getPhoneNumber() {
        return "";
    }

    @Override
    public CarInfo getCarInfo() {
        return new NullCarInfo();
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
