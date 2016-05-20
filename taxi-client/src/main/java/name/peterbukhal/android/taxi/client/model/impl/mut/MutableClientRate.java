package name.peterbukhal.android.taxi.client.model.impl.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.ClientRate;
import name.peterbukhal.android.taxi.client.model.impl.ClientRateImpl;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class MutableClientRate implements ClientRate {

    private Long rate;
    private String comment;

    public MutableClientRate() {
        this.rate = 0L;
        this.comment = "";
    }

    public MutableClientRate(Long rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    public MutableClientRate(ClientRate clientRate) {
        this.rate = clientRate.getRate();
        this.comment = clientRate.getComment();
    }

    protected MutableClientRate(Parcel parcel) {
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

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
            return new MutableClientRate(in);
        }

        @Override
        public ClientRate[] newArray(int size) {
            return new ClientRate[size];
        }

    };

}
