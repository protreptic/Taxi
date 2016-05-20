package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.ClientRate;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class ClientRateImpl implements ClientRate {

    private final Long rate;
    private final String comment;

    public ClientRateImpl(Long rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    public ClientRateImpl(ClientRate clientRate) {
        this.rate = clientRate.getRate();
        this.comment = clientRate.getComment();
    }

    protected ClientRateImpl(Parcel parcel) {
        rate = parcel.readLong();
        comment = parcel.readString();
    }

    @Override
    public Long getRate() {
        return rate;
    }

    @Override
    public String getComment() {
        return comment;
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
        dest.writeLong(rate);
        dest.writeString(comment);
    }

    public static final Creator<ClientRate> CREATOR = new Creator<ClientRate>() {

        @Override
        public ClientRate createFromParcel(Parcel in) {
            return new ClientRateImpl(in);
        }

        @Override
        public ClientRate[] newArray(int size) {
            return new ClientRate[size];
        }

    };

}
