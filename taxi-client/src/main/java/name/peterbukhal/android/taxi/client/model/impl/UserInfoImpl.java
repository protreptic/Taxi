package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
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
public class UserInfoImpl implements UserInfo, Parcelable {

    private String name;
    private Long bonuses;
    private String phoneNumber;
    private Long bonusValid;
    private Long bonusRate;
    private Long friendBonusRate;
    private Long clientRegisterPresent;
    private List<Tariff> tariffs = new ArrayList<>();
    private String friendShareText;
    private String friendShareUrl;
    private Promo promo;

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
}
