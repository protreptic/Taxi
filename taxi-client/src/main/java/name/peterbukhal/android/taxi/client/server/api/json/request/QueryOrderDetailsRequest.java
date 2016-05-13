package name.peterbukhal.android.taxi.client.server.api.json.request;

import name.peterbukhal.android.taxi.client.model.Order;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class QueryOrderDetailsRequest {

    private String token;
    private Order order;

    public QueryOrderDetailsRequest(String token, Order order) {
        this.token = token;
        this.order = order;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
