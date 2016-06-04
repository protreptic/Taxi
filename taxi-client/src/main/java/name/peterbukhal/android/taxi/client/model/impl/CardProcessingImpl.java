package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CardProcessing;
import name.peterbukhal.android.taxi.client.model.mut.MutableCardProcessing;
import name.peterbukhal.android.taxi.client.model.Mutable;

/**
 * Created by
 *      petronic on 19.05.16.
 */
public final class CardProcessingImpl implements CardProcessing, Mutable<CardProcessing> {

    private final Long id;
    private final Boolean manualPayment;

    public CardProcessingImpl(Long id, Boolean manualPayment) {
        this.id = id;
        this.manualPayment = manualPayment;
    }

    public CardProcessingImpl(CardProcessing cardProcessing) {
        this.id = cardProcessing.getId();
        this.manualPayment = cardProcessing.getManualPayment();
    }

    protected CardProcessingImpl(Parcel in) {
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
            return new CardProcessingImpl(source);
        }

        @Override
        public CardProcessing[] newArray(int size) {
            return new CardProcessing[size];
        }

    };

    @Override
    public CardProcessing toMutable() {
        return new MutableCardProcessing(this);
    }

}
