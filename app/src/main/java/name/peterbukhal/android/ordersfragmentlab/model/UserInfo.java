package name.peterbukhal.android.ordersfragmentlab.model;

import java.util.List;

/**
 * Created by
 * petronic on 25.03.16.
 */
public interface UserInfo {

    String getName();
    Long getBonuses();
    String getPhoneNumber();
    Long getBonusValid();
    Long getBonusRate();
    Long getFriendBonusRate();
    Long getClientRegisterPresent();
    List<Tariff> getTariffs();
    String getFriendShareText();
    String getFriendShareUrl();
    Promo getPromo();

}
