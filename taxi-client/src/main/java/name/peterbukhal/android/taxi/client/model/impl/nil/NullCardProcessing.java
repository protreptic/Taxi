package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.CardProcessing;

/**
 * Created by
 *      petronic on 19.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullCardProcessing implements CardProcessing {

    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public Boolean getManualPayment() {
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
