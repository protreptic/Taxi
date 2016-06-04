package name.peterbukhal.android.taxi.client.server.api.json.request;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public final class SubmitSmsCodeRequest extends EmptyRequest {

    private final String name;
    private final Integer source;
    private final Integer smsCode;
    private final String phoneNumber;
    private final String promoCode;

    public SubmitSmsCodeRequest(String token, String name, Integer source, Integer smsCode, String phoneNumber, String promoCode) {
        super(token);

        this.name = name;
        this.source = source;
        this.smsCode = smsCode;
        this.phoneNumber = phoneNumber;
        this.promoCode = promoCode;
    }

    public String getName() {
        return name;
    }

    public Integer getSource() {
        return source;
    }

    public Integer getSmsCode() {
        return smsCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPromoCode() {
        return promoCode;
    }

}
