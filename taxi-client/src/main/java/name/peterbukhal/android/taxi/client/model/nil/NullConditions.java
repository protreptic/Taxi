package name.peterbukhal.android.taxi.client.model.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Conditions;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullConditions implements Conditions {

    @Override
    public Long getCarCategory() {
        return 0L;
    }

    @Override
    public Boolean getConditioner() {
        return false;
    }

    @Override
    public Long getSmoking() {
        return 0L;
    }

    @Override
    public Boolean getBaggage() {
        return false;
    }

    @Override
    public Boolean getAnimals() {
        return false;
    }

    @Override
    public Boolean getUniversal() {
        return false;
    }

    @Override
    public Long getChildSit() {
        return 0L;
    }

    @Override
    public Boolean getPaymentSlip() {
        return false;
    }

    @Override
    public Boolean getCreditCard() {
        return false;
    }

    @Override
    public Boolean getBonusPay() {
        return false;
    }

    @Override
    public Boolean getYellowNumbers() {
        return false;
    }

    @Override
    public Boolean getInAppPay() {
        return false;
    }

    @Override
    public Boolean isNull() {
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
