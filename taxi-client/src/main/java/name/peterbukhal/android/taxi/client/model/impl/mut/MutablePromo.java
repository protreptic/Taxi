package name.peterbukhal.android.taxi.client.model.impl.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Promo;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class MutablePromo implements Promo {

    private String promoCode;
    private Long inviteBonus;
    private Long selfBonus;
    private Long orderMinPrice;
    private String appText;
    private String socialText;
    private String twitterText;

    public MutablePromo() {
        this.promoCode = "";
        this.inviteBonus = 0L;
        this.selfBonus = 0L;
        this.orderMinPrice = 0L;
        this.appText = "";
        this.socialText = "";
        this.twitterText = "";
    }

    public MutablePromo(String promoCode, Long inviteBonus, Long selfBonus, Long orderMinPrice,
                     String appText, String socialText, String twitterText) {
        this.promoCode = promoCode;
        this.inviteBonus = inviteBonus;
        this.selfBonus = selfBonus;
        this.orderMinPrice = orderMinPrice;
        this.appText = appText;
        this.socialText = socialText;
        this.twitterText = twitterText;
    }

    public MutablePromo(Promo promo) {
        this.promoCode = promo.getPromoCode();
        this.inviteBonus = promo.getInvitedBonus();
        this.selfBonus = promo.getSelfBonus();
        this.orderMinPrice = promo.getOrderMinPrice();
        this.appText = promo.getAppText();
        this.socialText = promo.getSocialText();
        this.twitterText = promo.getTwitterText();
    }

    protected MutablePromo(Parcel in) {
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

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public void setInviteBonus(Long inviteBonus) {
        this.inviteBonus = inviteBonus;
    }

    public void setSelfBonus(Long selfBonus) {
        this.selfBonus = selfBonus;
    }

    public void setOrderMinPrice(Long orderMinPrice) {
        this.orderMinPrice = orderMinPrice;
    }

    public void setAppText(String appText) {
        this.appText = appText;
    }

    public void setSocialText(String socialText) {
        this.socialText = socialText;
    }

    public void setTwitterText(String twitterText) {
        this.twitterText = twitterText;
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
            return new MutablePromo(in);
        }

        @Override
        public Promo[] newArray(int size) {
            return new Promo[size];
        }

    };

}
