package name.peterbukhal.android.taxi.client.model.impl.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CardProcessing;
import name.peterbukhal.android.taxi.client.model.impl.CardProcessingImpl;
import name.peterbukhal.android.taxi.client.unused.Immutable;

/**
 * Created by
 *      petronic on 19.05.16.
 */
public final class MutableCardProcessing implements CardProcessing, Immutable<CardProcessing> {

    private Long id;
    private Boolean manualPayment;

    public MutableCardProcessing() {
        this.id = 0L;
        this.manualPayment = false;
    }

    public MutableCardProcessing(Long id, Boolean manualPayment) {
        this.id = id;
        this.manualPayment = manualPayment;
    }

    public MutableCardProcessing(CardProcessing cardProcessing) {
        this.id = cardProcessing.getId();
        this.manualPayment = cardProcessing.getManualPayment();
    }

    protected MutableCardProcessing(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.manualPayment = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Boolean getManualPayment() {
        return manualPayment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setManualPayment(Boolean manualPayment) {
        this.manualPayment = manualPayment;
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
        dest.writeValue(this.id);
        dest.writeValue(this.manualPayment);
    }

    public static final Creator<CardProcessing> CREATOR = new Creator<CardProcessing>() {

        @Override
        public CardProcessing createFromParcel(Parcel source) {
            return new MutableCardProcessing(source);
        }

        @Override
        public CardProcessing[] newArray(int size) {
            return new CardProcessing[size];
        }

    };

    @Override
    public CardProcessing toImmutable() {
        return new CardProcessingImpl(this);
    }

}
