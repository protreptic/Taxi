package name.peterbukhal.android.ordersfragmentlab.model;

import android.os.Parcelable;

/**
 * Created by
 * petronic on 25.03.16.
 */
public interface Promo extends Parcelable {

    String getPromoCode();
    Long getInvitedBonus();
    Long getSelfBonus();
    Long getOrderMinPrice();
    String getAppText();
    String getSocialText();
    String getTwitterText();

}
