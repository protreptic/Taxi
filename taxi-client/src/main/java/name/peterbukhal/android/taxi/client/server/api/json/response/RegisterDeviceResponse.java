package name.peterbukhal.android.taxi.client.server.api.json.response;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class RegisterDeviceResponse {

    private Boolean status;

    public RegisterDeviceResponse() {
        status = false;
    }

    public RegisterDeviceResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
