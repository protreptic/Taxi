package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.Promo;
import name.peterbukhal.android.taxi.client.model.Tariff;
import name.peterbukhal.android.taxi.client.model.UserInfo;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public final class UserInfoImpl implements UserInfo {

    private final String name;
    private final Long bonuses;
    private final String phoneNumber;
    private final Long bonusValid;
    private final Long bonusRate;
    private final Long friendBonusRate;
    private final Long clientRegisterPresent;
    private final List<Tariff> tariffs;
    private final String friendShareText;
    private final String friendShareUrl;
    private final Promo promo;

    public UserInfoImpl(String name, Long bonuses, String phoneNumber, Long bonusValid, Long bonusRate,
                        Long friendBonusRate, Long clientRegisterPresent, List<Tariff> tariffs,
                        String friendShareText, String friendShareUrl, Promo promo) {
        this.name = name;
        this.bonuses = bonuses;
        this.phoneNumber = phoneNumber;
        this.bonusValid = bonusValid;
        this.bonusRate = bonusRate;
        this.friendBonusRate = friendBonusRate;
        this.clientRegisterPresent = clientRegisterPresent;
        this.tariffs = Collections.unmodifiableList(tariffs);
        this.friendShareText = friendShareText;
        this.friendShareUrl = friendShareUrl;
        this.promo = promo;
    }

    public UserInfoImpl(UserInfo userInfo) {
        this.name = userInfo.getName();
        this.bonuses = userInfo.getBonuses();
        this.phoneNumber = userInfo.getPhoneNumber();
        this.bonusValid = userInfo.getBonusValid();
        this.bonusRate = userInfo.getBonusRate();
        this.friendBonusRate = userInfo.getFriendBonusRate();
        this.clientRegisterPresent = userInfo.getClientRegisterPresent();
        this.tariffs = Collections.unmodifiableList(userInfo.getTariffs());
        this.friendShareText = userInfo.getFriendShareText();
        this.friendShareUrl = userInfo.getFriendShareUrl();
        this.promo = userInfo.getPromo();
    }

    protected UserInfoImpl(Parcel in) {
        name = in.readString();
        bonuses = in.readLong();
        phoneNumber = in.readString();
        bonusValid = in.readLong();
        bonusRate = in.readLong();
        friendBonusRate = in.readLong();
        clientRegisterPresent = in.readLong();
        tariffs = Collections.unmodifiableList(Arrays.asList((Tariff[]) in.readParcelableArray(null)));
        friendShareText = in.readString();
        friendShareUrl = in.readString();
        promo = in.readParcelable(null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getBonuses() {
        return bonuses;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public Long getBonusValid() {
        return bonusValid;
    }

    @Override
    public Long getBonusRate() {
        return bonusRate;
    }

    @Override
    public Long getFriendBonusRate() {
        return friendBonusRate;
    }

    @Override
    public Long getClientRegisterPresent() {
        return clientRegisterPresent;
    }

    @Override
    public List<Tariff> getTariffs() {
        return Collections.unmodifiableList(tariffs);
    }

    @Override
    public String getFriendShareText() {
        return friendShareText;
    }

    @Override
    public String getFriendShareUrl() {
        return friendShareUrl;
    }

    @Override
    public Promo getPromo() {
        return promo;
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
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(friendShareText);
        dest.writeString(friendShareUrl);
    }

    public static final Creator<UserInfoImpl> CREATOR = new Creator<UserInfoImpl>() {

        @Override
        public UserInfoImpl createFromParcel(Parcel in) {
            return new UserInfoImpl(in);
        }

        @Override
        public UserInfoImpl[] newArray(int size) {
            return new UserInfoImpl[size];
        }

    };

}
