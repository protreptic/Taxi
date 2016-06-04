package name.peterbukhal.android.taxi.client.model.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Conditions;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class MutableConditions implements Conditions {

    private Long carCategory;
    private Boolean conditioner;
    private Long smoking;
    private Boolean baggage;
    private Boolean animals;
    private Boolean universal;
    private Long childSit;
    private Boolean paymentSlip;
    private Boolean creditCard;
    private Boolean bonusPay;
    private Boolean yellowNumbers;
    private Boolean inAppPay;

    public MutableConditions() {
        this.carCategory = 0L;
        this.conditioner = false;
        this.smoking = 0L;
        this.baggage = false;
        this.animals = false;
        this.universal = false;
        this.childSit = 0L;
        this.paymentSlip = false;
        this.creditCard = false;
        this.bonusPay = false;
        this.yellowNumbers = false;
        this.inAppPay = false;
    }

    public MutableConditions(Long carCategory, Boolean conditioner, Long smoking, Boolean baggage,
                          Boolean animals, Boolean universal, Long childSit, Boolean paymentSlip,
                          Boolean creditCard, Boolean bonusPay, Boolean yellowNumbers,
                          Boolean inAppPay) {
        this.carCategory = carCategory;
        this.conditioner = conditioner;
        this.smoking = smoking;
        this.baggage = baggage;
        this.animals = animals;
        this.universal = universal;
        this.childSit = childSit;
        this.paymentSlip = paymentSlip;
        this.creditCard = creditCard;
        this.bonusPay = bonusPay;
        this.yellowNumbers = yellowNumbers;
        this.inAppPay = inAppPay;
    }

    public MutableConditions(Conditions conditions) {
        this.carCategory = conditions.getCarCategory();
        this.conditioner = conditions.getConditioner();
        this.smoking = conditions.getSmoking();
        this.baggage = conditions.getBaggage();
        this.animals = conditions.getAnimals();
        this.universal = conditions.getUniversal();
        this.childSit = conditions.getChildSit();
        this.paymentSlip = conditions.getPaymentSlip();
        this.creditCard = conditions.getCreditCard();
        this.bonusPay = conditions.getBonusPay();
        this.yellowNumbers = conditions.getYellowNumbers();
        this.inAppPay = conditions.getInAppPay();
    }

    protected MutableConditions(Parcel parcel) {
        this.carCategory = parcel.readLong();
        this.conditioner = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.smoking = parcel.readLong();
        this.baggage = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.animals = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.universal = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.childSit = parcel.readLong();
        this.paymentSlip = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.creditCard = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.bonusPay = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.yellowNumbers = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.inAppPay = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
    }

    @Override
    public Long getCarCategory() {
        return carCategory;
    }

    @Override
    public Boolean getConditioner() {
        return conditioner;
    }

    @Override
    public Long getSmoking() {
        return smoking;
    }

    @Override
    public Boolean getBaggage() {
        return baggage;
    }

    @Override
    public Boolean getAnimals() {
        return animals;
    }

    @Override
    public Boolean getUniversal() {
        return universal;
    }

    @Override
    public Long getChildSit() {
        return childSit;
    }

    @Override
    public Boolean getPaymentSlip() {
        return paymentSlip;
    }

    @Override
    public Boolean getCreditCard() {
        return creditCard;
    }

    @Override
    public Boolean getBonusPay() {
        return bonusPay;
    }

    @Override
    public Boolean getYellowNumbers() {
        return yellowNumbers;
    }

    @Override
    public Boolean getInAppPay() {
        return inAppPay;
    }

    public void setCarCategory(Long carCategory) {
        this.carCategory = carCategory;
    }

    public void setConditioner(Boolean conditioner) {
        this.conditioner = conditioner;
    }

    public void setSmoking(Long smoking) {
        this.smoking = smoking;
    }

    public void setBaggage(Boolean baggage) {
        this.baggage = baggage;
    }

    public void setAnimals(Boolean animals) {
        this.animals = animals;
    }

    public void setUniversal(Boolean universal) {
        this.universal = universal;
    }

    public void setChildSit(Long childSit) {
        this.childSit = childSit;
    }

    public void setPaymentSlip(Boolean paymentSlip) {
        this.paymentSlip = paymentSlip;
    }

    public void setCreditCard(Boolean creditCard) {
        this.creditCard = creditCard;
    }

    public void setBonusPay(Boolean bonusPay) {
        this.bonusPay = bonusPay;
    }

    public void setYellowNumbers(Boolean yellowNumbers) {
        this.yellowNumbers = yellowNumbers;
    }

    public void setInAppPay(Boolean inAppPay) {
        this.inAppPay = inAppPay;
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
        dest.writeLong(carCategory);
        dest.writeValue(conditioner);
        dest.writeLong(smoking);
        dest.writeValue(baggage);
        dest.writeValue(animals);
        dest.writeValue(universal);
        dest.writeLong(childSit);
        dest.writeValue(paymentSlip);
        dest.writeValue(creditCard);
        dest.writeValue(bonusPay);
        dest.writeValue(yellowNumbers);
        dest.writeValue(inAppPay);
    }

    public static final Creator<Conditions> CREATOR = new Creator<Conditions>() {

        @Override
        public Conditions createFromParcel(Parcel in) {
            return new MutableConditions(in);
        }

        @Override
        public Conditions[] newArray(int size) {
            return new Conditions[size];
        }

    };

}
