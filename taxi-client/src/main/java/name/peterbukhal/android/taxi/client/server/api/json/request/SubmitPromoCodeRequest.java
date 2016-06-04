package name.peterbukhal.android.taxi.client.server.api.json.request;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public final class SubmitPromoCodeRequest extends EmptyRequest {

    private final String promoCode;

    public SubmitPromoCodeRequest(String token, String promoCode) {
        super(token);

        this.promoCode = promoCode;
    }

    public String getPromoCode() {
        return promoCode;
    }

}
