package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;
import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.model.Tariff;

/**
 * Created by
 * petronic on 25.03.16.
 */
public class TariffImpl implements Tariff, Parcelable {

    private Long id;
    private String name;
    private Long rate;
    private Long order;

    public TariffImpl() {}

    protected TariffImpl(Parcel in) {
        id = in.readLong();
        name = in.readString();
        rate = in.readLong();
        order = in.readLong();
    }

    public static final Creator<TariffImpl> CREATOR = new Creator<TariffImpl>() {
        @Override
        public TariffImpl createFromParcel(Parcel in) {
            return new TariffImpl(in);
        }

        @Override
        public TariffImpl[] newArray(int size) {
            return new TariffImpl[size];
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

    @Override
    public Long getRate() {
        return rate;
    }

    @Override
    public Long getOrder() {
        return order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(rate);
        dest.writeLong(order);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public void setOrder(Long order) {
        this.order = order;
    }
}