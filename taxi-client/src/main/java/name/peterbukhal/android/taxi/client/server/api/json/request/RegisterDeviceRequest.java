package name.peterbukhal.android.taxi.client.server.api.json.request;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class RegisterDeviceRequest {

    public enum DevicePlatform {
        ANDROID(0),
        @SuppressWarnings("unused") IOS(1);

        private int value;

        DevicePlatform(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    private String token;
    private String deviceToken;
    private DevicePlatform platform;

    public RegisterDeviceRequest(String token, String deviceToken, DevicePlatform platform) {
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

    public DevicePlatform getDevicePlatform() {
        return platform;
    }

    public void setDevicePlatform(DevicePlatform devicePlatform) {
        this.platform = devicePlatform;
    }

}
