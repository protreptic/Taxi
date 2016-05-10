package name.peterbukhal.android.ordersfragmentlab.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.ordersfragmentlab.model.ClientRate;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class ClientRateImpl implements ClientRate {

    private Long rate;
    private String comment;

    public ClientRateImpl() {
        rate = 0L;
        comment = "";
    }

    public ClientRateImpl(Long rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    protected ClientRateImpl(Parcel parcel) {
        rate = parcel.readLong();
        comment = parcel.readString();
    }

    @Override
    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
