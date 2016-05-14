package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CarInfo;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public final class CarInfoImpl implements CarInfo {

    private final String name;
    private final String color;
    private final Long category;
    private final String number;

    public CarInfoImpl(String name, String color, Long category, String number) {
        this.name = name;
        this.color = color;
        this.category = category;
        this.number = number;
    }

    public CarInfoImpl(CarInfo carInfo) {
        this.name = carInfo.getName();
        this.color = carInfo.getColor();
        this.category = carInfo.getCategory();
        this.number = carInfo.getNumber();
    }

    protected CarInfoImpl(Parcel in) {
        this.name = in.readString();
        this.color = in.readString();
        this.category = in.readLong();
        this.number = in.readString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public Long getCategory() {
        return category;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(color);
        dest.writeLong(category);
        dest.writeString(number);
    }

    public static final Creator<CarInfo> CREATOR = new Creator<CarInfo>() {

        @Override
        public CarInfo createFromParcel(Parcel in) {
            return new CarInfoImpl(in);
        }

        @Override
        public CarInfo[] newArray(int size) {
            return new CarInfo[size];
        }

    };

}
