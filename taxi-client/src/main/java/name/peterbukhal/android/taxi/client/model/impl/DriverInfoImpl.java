package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CarInfo;
import name.peterbukhal.android.taxi.client.model.DriverInfo;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public final class DriverInfoImpl implements DriverInfo {

    private final Long id;
    private final String name;
    private final Float rating;
    private final Long ratingCount;
    private final String phoneNumber;
    private final CarInfo carInfo;

    public DriverInfoImpl(Long id, String name, Float rating, Long ratingCount,
                          String phoneNumber, CarInfo carInfo) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.phoneNumber = phoneNumber;
        this.carInfo = new CarInfoImpl(carInfo);
    }

    public DriverInfoImpl(DriverInfo driverInfo) {
        this.id = driverInfo.getId();
        this.name = driverInfo.getName();
        this.rating = driverInfo.getRating();
        this.ratingCount = driverInfo.getRatingCount();
        this.phoneNumber = driverInfo.getPhoneNumber();
        this.carInfo = new CarInfoImpl(driverInfo.getCarInfo());
    }

    protected DriverInfoImpl(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.rating = in.readFloat();
        this.ratingCount = in.readLong();
        this.phoneNumber = in.readString();
        this.carInfo = in.readParcelable(CarInfoImpl.class.getClassLoader());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Float getRating() {
        return rating;
    }

    @Override
    public Long getRatingCount() {
        return ratingCount;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public CarInfo getCarInfo() {
        return carInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<DriverInfo> CREATOR = new Creator<DriverInfo>() {

        @Override
        public DriverInfo createFromParcel(Parcel in) {
            return new DriverInfoImpl(in);
        }

        @Override
        public DriverInfo[] newArray(int size) {
            return new DriverInfo[size];
        }

    };

}
