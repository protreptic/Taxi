package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.Cities;
import name.peterbukhal.android.taxi.client.model.City;

/**
 * Created by
 *      petronic on 07.05.16.
 */
public final class CitiesImpl implements Cities {

    private final List<City> cities;

    public CitiesImpl(List<City> cities) {
        this.cities = Collections.unmodifiableList(cities);
    }

    public CitiesImpl(Cities cities) {
        this.cities = Collections.unmodifiableList(cities.getCities());
    }

    protected CitiesImpl(Parcel parcel) {
        this.cities = Collections.unmodifiableList(parcel.createTypedArrayList(CityImpl.CREATOR));
    }

    @Override
    public List<City> getCities() {
        return Collections.unmodifiableList(cities);
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(cities);
    }

    public static final Creator<Cities> CREATOR = new Creator<Cities>() {

        @Override
        public Cities createFromParcel(Parcel in) {
            return new CitiesImpl(in);
        }

        @Override
        public Cities[] newArray(int size) {
            return new Cities[size];
        }

    };

}
