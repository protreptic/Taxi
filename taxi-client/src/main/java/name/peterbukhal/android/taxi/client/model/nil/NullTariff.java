package name.peterbukhal.android.taxi.client.model.nil;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Tariff;

/**
 * Created by
 * petronic on 01.06.16.
 */
public class NullTariff implements Tariff {

    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Long getRate() {
        return 0L;
    }

    @Override
    public Long getOrder() {
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
