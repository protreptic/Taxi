package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CarInfo;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullCarInfo implements CarInfo {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getColor() {
        return "";
    }

    @Override
    public Long getCategory() {
        return 0L;
    }

    @Override
    public String getNumber() {
        return "";
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
