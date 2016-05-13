package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.City;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CityImpl implements City {

    private Long id;
    private String name;

    public CityImpl(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected CityImpl(Parcel parcel) {
        id = parcel.readLong();
        name = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }

    public static final Creator<City> CREATOR = new Creator<City>() {

        @Override
        public City createFromParcel(Parcel in) {
            return new CityImpl(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }

    };

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

}
