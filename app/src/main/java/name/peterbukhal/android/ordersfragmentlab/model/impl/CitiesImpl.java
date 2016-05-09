package name.peterbukhal.android.ordersfragmentlab.model.impl;

import android.os.Parcel;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.ordersfragmentlab.model.Cities;
import name.peterbukhal.android.ordersfragmentlab.model.City;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class CitiesImpl implements Cities {

    private List<City> cities = Collections.emptyList();

    public CitiesImpl(List<City> cities) {
        this.cities = Collections.unmodifiableList(cities);
    }

    protected CitiesImpl(Parcel parcel) {
        this.cities = Collections.unmodifiableList(parcel.createTypedArrayList(CityImpl.CREATOR));
    }

    @Override
    public List<City> getCities() {
        return Collections.unmodifiableList(cities);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(cities);
    }

}
