package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Tariff;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public final class TariffImpl implements Tariff {

    private final Long id;
    private final String name;
    private final Long rate;
    private final Long order;

    public TariffImpl(Long id, String name, Long rate, Long order) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.order = order;
    }

    public TariffImpl(Tariff tariff) {
        this.id = tariff.getId();
        this.name = tariff.getName();
        this.rate = tariff.getRate();
        this.order = tariff.getOrder();
    }

    protected TariffImpl(Parcel in) {
        id = in.readLong();
        name = in.readString();
        rate = in.readLong();
        order = in.readLong();
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
    public Long getRate() {
        return rate;
    }

    @Override
    public Long getOrder() {
        return order;
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
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(rate);
        dest.writeLong(order);
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

}