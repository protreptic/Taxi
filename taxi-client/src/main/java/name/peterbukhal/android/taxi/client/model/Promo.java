package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.unused.Nullable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface Promo extends Parcelable, Nullable {
    String getPromoCode();
    Long getInvitedBonus();
    Long getSelfBonus();
    Long getOrderMinPrice();
    String getAppText();
    String getSocialText();
    String getTwitterText();
}
