package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.Cities;
import name.peterbukhal.android.taxi.client.model.City;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullCities implements Cities {

    @Override
    public List<City> getCities() {
        return Collections.emptyList();
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
