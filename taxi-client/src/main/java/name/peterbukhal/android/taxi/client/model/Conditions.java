package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.unused.Nullable;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public interface Conditions extends Parcelable, Nullable {
    Long getCarCategory();
    Boolean getConditioner();
    Long getSmoking();
    Boolean getBaggage();
    Boolean getAnimals();
    Boolean getUniversal();
    Long getChildSit();
    Boolean getPaymentSlip();
    Boolean getCreditCard();
    Boolean getBonusPay();
    Boolean getYellowNumbers();
    Boolean getInAppPay();
}
