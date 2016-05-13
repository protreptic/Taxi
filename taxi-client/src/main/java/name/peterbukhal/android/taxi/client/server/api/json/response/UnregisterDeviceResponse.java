package name.peterbukhal.android.taxi.client.server.api.json.response;

/**
 * Created by
 * petronic on 12.05.16.
 */
public class UnregisterDeviceResponse {

    private Boolean status;

    public UnregisterDeviceResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
