package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.Conditions;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public final class ConditionsImpl implements Conditions {

    private final Long carCategory;
    private final Boolean conditioner;
    private final Long smoking;
    private final Boolean baggage;
    private final Boolean animals;
    private final Boolean universal;
    private final Long childSit;
    private final Boolean paymentSlip;
    private final Boolean creditCard;
    private final Boolean bonusPay;
    private final Boolean yellowNumbers;
    private final Boolean inAppPay;

    public ConditionsImpl(Long carCategory, Boolean conditioner, Long smoking, Boolean baggage,
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

    public ConditionsImpl(Conditions conditions) {
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

    protected ConditionsImpl(Parcel parcel) {
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

    public static final Creator<Conditions> CREATOR = new Creator<Conditions>() {

        @Override
        public Conditions createFromParcel(Parcel in) {
            return new ConditionsImpl(in);
        }

        @Override
        public Conditions[] newArray(int size) {
            return new Conditions[size];
        }

    };

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

    public Boolean getInAppPay() {
        return inAppPay;
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

}
