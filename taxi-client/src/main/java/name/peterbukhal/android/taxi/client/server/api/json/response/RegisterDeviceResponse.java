package name.peterbukhal.android.taxi.client.server.api.json.response;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class RegisterDeviceResponse {

    private final Boolean status;

    public RegisterDeviceResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

}
