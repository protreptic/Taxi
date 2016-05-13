package name.peterbukhal.android.taxi.client.server.api.json.request;

/**
 * Created by
 * petronic on 12.05.16.
 */
public class UnregisterDeviceRequest {

    private String token;

    public UnregisterDeviceRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
