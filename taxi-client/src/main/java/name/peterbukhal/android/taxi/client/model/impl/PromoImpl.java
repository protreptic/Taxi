package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Promo;

/**
 * Created by
 * petronic on 25.03.16.
 */
public class PromoImpl implements Promo {

    private String promoCode;
    private Long inviteBonus;
    private Long selfBonus;
    private Long orderMinPrice;
    private String appText;
    private String socialText;
    private String twitterText;

    protected PromoImpl(Parcel in) {
        promoCode = in.readString();
        inviteBonus = in.readLong();
        selfBonus = in.readLong();
        orderMinPrice = in.readLong();
        appText = in.readString();
        socialText = in.readString();
        twitterText = in.readString();
    }

    public static final Creator<PromoImpl> CREATOR = new Creator<PromoImpl>() {
        @Override
        public PromoImpl createFromParcel(Parcel in) {
            return new PromoImpl(in);
        }

        @Override
        public PromoImpl[] newArray(int size) {
            return new PromoImpl[size];
        }
    };

    @Override
    public String getPromoCode() {
        return promoCode;
    }

    @Override
    public Long getInvitedBonus() {
        return inviteBonus;
    }

    @Override
    public Long getSelfBonus() {
        return selfBonus;
    }

    @Override
    public Long getOrderMinPrice() {
        return orderMinPrice;
    }

    @Override
    public String getAppText() {
        return appText;
    }

    @Override
    public String getSocialText() {
        return socialText;
    }

    @Override
    public String getTwitterText() {
        return twitterText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(promoCode);
        dest.writeLong(inviteBonus);
        dest.writeLong(selfBonus);
        dest.writeLong(orderMinPrice);
        dest.writeString(appText);
        dest.writeString(socialText);
        dest.writeString(twitterText);
    }
}
