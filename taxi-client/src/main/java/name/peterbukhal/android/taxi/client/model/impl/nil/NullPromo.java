package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Promo;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullPromo implements Promo {

    @Override
    public String getPromoCode() {
        return "";
    }

    @Override
    public Long getInvitedBonus() {
        return 0L;
    }

    @Override
    public Long getSelfBonus() {
        return 0L;
    }

    @Override
    public Long getOrderMinPrice() {
        return 0L;
    }

    @Override
    public String getAppText() {
        return "";
    }

    @Override
    public String getSocialText() {
        return "";
    }

    @Override
    public String getTwitterText() {
        return "";
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
