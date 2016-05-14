package name.peterbukhal.android.taxi.client.server.api.json.request;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.Conditions;
import name.peterbukhal.android.taxi.client.model.RoutePoint;
import name.peterbukhal.android.taxi.client.model.impl.ConditionsImpl;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public final class SubmitOrderCreateRequest {

    private final String token;
    private final List<RoutePoint> routePoints;
    private final String comment;
    private final Long departureTime;
    private final Conditions conditions;
    private final Long fixedPrice;
    private final Long source;
    private final String phoneNumber;
    private final String routeJson;

    public SubmitOrderCreateRequest(String token, List<RoutePoint> routePoints, String comment,
                                    Long departureTime, Conditions conditions, Long fixedPrice,
                                    Long source, String phoneNumber, String routeJson) {
        this.token = token;
        this.routePoints = Collections.unmodifiableList(routePoints);
        this.comment = comment;
        this.departureTime = departureTime;
        this.conditions = new ConditionsImpl(conditions);
        this.fixedPrice = fixedPrice;
        this.source = source;
        this.phoneNumber = phoneNumber;
        this.routeJson = routeJson;
    }

    public String getToken() {
        return token;
    }

    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public String getComment() {
        return comment;
    }

    public Long getDepartureTime() {
        return departureTime;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public Long getFixedPrice() {
        return fixedPrice;
    }

    public Long getSource() {
        return source;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRouteJson() {
        return routeJson;
    }

}
