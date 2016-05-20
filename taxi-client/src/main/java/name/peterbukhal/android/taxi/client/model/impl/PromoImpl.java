package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Promo;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public final class PromoImpl implements Promo {

    private final String promoCode;
    private final Long inviteBonus;
    private final Long selfBonus;
    private final Long orderMinPrice;
    private final String appText;
    private final String socialText;
    private final String twitterText;

    public PromoImpl(String promoCode, Long inviteBonus, Long selfBonus, Long orderMinPrice,
                     String appText, String socialText, String twitterText) {
        this.promoCode = promoCode;
        this.inviteBonus = inviteBonus;
        this.selfBonus = selfBonus;
        this.orderMinPrice = orderMinPrice;
        this.appText = appText;
        this.socialText = socialText;
        this.twitterText = twitterText;
    }

    public PromoImpl(Promo promo) {
        this.promoCode = promo.getPromoCode();
        this.inviteBonus = promo.getInvitedBonus();
        this.selfBonus = promo.getSelfBonus();
        this.orderMinPrice = promo.getOrderMinPrice();
        this.appText = promo.getAppText();
        this.socialText = promo.getSocialText();
        this.twitterText = promo.getTwitterText();
    }

    protected PromoImpl(Parcel in) {
        promoCode = in.readString();
        inviteBonus = in.readLong();
        selfBonus = in.readLong();
        orderMinPrice = in.readLong();
        appText = in.readString();
        socialText = in.readString();
        twitterText = in.readString();
    }

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
    public Boolean isNull() {
        return false;
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

    public static final Creator<Promo> CREATOR = new Creator<Promo>() {

        @Override
        public Promo createFromParcel(Parcel in) {
            return new PromoImpl(in);
        }

        @Override
        public Promo[] newArray(int size) {
            return new Promo[size];
        }

    };

}
