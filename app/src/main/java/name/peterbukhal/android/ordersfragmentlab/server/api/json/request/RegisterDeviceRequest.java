package name.peterbukhal.android.ordersfragmentlab.server.api.json.request;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class RegisterDeviceRequest {

    private String token;
    private String deviceToken;
    private Long platform;

    public RegisterDeviceRequest(String token, String deviceToken, Long platform) {
        this.token = token;
        this.deviceToken = deviceToken;
        this.platform = platform;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Long getDevicePlatform() {
        return platform;
    }

    public void setDevicePlatform(Long devicePlatform) {
        this.platform = devicePlatform;
    }

}
