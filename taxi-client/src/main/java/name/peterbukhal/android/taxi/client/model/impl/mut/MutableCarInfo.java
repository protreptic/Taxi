package name.peterbukhal.android.taxi.client.model.impl.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CarInfo;
import name.peterbukhal.android.taxi.client.model.impl.CarInfoImpl;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class MutableCarInfo implements CarInfo {

    private String name;
    private String color;
    private Long category;
    private String number;

    public MutableCarInfo() {
        this.name = "";
        this.color = "";
        this.category = 0L;
        this.number = "";
    }

    public MutableCarInfo(String name, String color, Long category, String number) {
        this.name = name;
        this.color = color;
        this.category = category;
        this.number = number;
    }

    public MutableCarInfo(CarInfo carInfo) {
        this.name = carInfo.getName();
        this.color = carInfo.getColor();
        this.category = carInfo.getCategory();
        this.number = carInfo.getNumber();
    }

    protected MutableCarInfo(Parcel in) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public void setNumber(String number) {
        this.number = number;
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
        dest.writeString(name);
        dest.writeString(color);
        dest.writeLong(category);
        dest.writeString(number);
    }

    public static final Creator<CarInfo> CREATOR = new Creator<CarInfo>() {

        @Override
        public CarInfo createFromParcel(Parcel in) {
            return new MutableCarInfo(in);
        }

        @Override
        public CarInfo[] newArray(int size) {
            return new CarInfo[size];
        }

    };

}
