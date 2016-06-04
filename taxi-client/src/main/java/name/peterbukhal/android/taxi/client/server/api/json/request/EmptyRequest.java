package name.peterbukhal.android.taxi.client.server.api.json.request;

import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikService;

/**
 * Created by
 *      petronic on 02.06.16.
 */
public class EmptyRequest {

    private final Integer apiVersion;
    private final String token;

    public EmptyRequest(String token) {
        this.apiVersion = JsonTaxikService.API_VERSION;
        this.token = token;
    }

    public Integer getApiVersion() {
        return apiVersion;
    }

    public String getToken() {
        return token;
    }

}
