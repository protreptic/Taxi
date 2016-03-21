package name.peterbukhal.android.ordersfragmentlab.model.api.request;

/**
 * Created by petronic on 21.03.16.
 */
public class SubmitPhoneNumberRequest {

    private String phoneNumber;
    private String promoCode;

    public SubmitPhoneNumberRequest(String phoneNumber, String promoCode) {
        this.phoneNumber = phoneNumber;
        this.promoCode = promoCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPromoCode() {
        return promoCode;
    }

}
