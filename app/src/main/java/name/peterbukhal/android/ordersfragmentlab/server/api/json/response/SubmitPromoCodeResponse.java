package name.peterbukhal.android.ordersfragmentlab.server.api.json.response;

/**
 * Created by
 * petronic on 21.03.16.
 */
public class SubmitPromoCodeResponse {

    private Boolean status;

    public SubmitPromoCodeResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
