package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public interface Conditions extends Parcelable {

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
