package name.peterbukhal.android.taxi.client.server.api.json.response;

/**
 * Created by
 * petronic on 14.05.16.
 */
public final class SubmitOrderCreateResponse {

    private final Long orderId;

    public SubmitOrderCreateResponse(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

}
