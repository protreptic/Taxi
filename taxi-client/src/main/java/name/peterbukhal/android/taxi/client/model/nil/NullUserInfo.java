package name.peterbukhal.android.taxi.client.model.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.Promo;
import name.peterbukhal.android.taxi.client.model.Tariff;
import name.peterbukhal.android.taxi.client.model.UserInfo;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullUserInfo implements UserInfo {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Long getBonuses() {
        return 0L;
    }

    @Override
    public String getPhoneNumber() {
        return "";
    }

    @Override
    public Long getBonusValid() {
        return 0L;
    }

    @Override
    public Long getBonusRate() {
        return 0L;
    }

    @Override
    public Long getFriendBonusRate() {
        return 0L;
    }

    @Override
    public Long getClientRegisterPresent() {
        return 0L;
    }

    @Override
    public List<Tariff> getTariffs() {
        return Collections.emptyList();
    }

    @Override
    public String getFriendShareText() {
        return "";
    }

    @Override
    public String getFriendShareUrl() {
        return "";
    }

    @Override
    public Promo getPromo() {
        return new NullPromo();
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
