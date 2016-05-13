package name.peterbukhal.android.taxi.client.server.api.json.request;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitPromoCodeRequest {

    private String token;
    private String promoCode;

    public SubmitPromoCodeRequest(String token, String promoCode) {
        this.token = token;
        this.promoCode = promoCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

}
