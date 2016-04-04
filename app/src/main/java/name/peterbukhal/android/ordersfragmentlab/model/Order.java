package name.peterbukhal.android.ordersfragmentlab.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by
 * petronic on 25.03.16.
 */
public interface Order extends Parcelable {

    Long getId();
    List<RoutePoint> getRoutePoints();
    Long getApproximatePrice();
    Long getFixedPrice();
    Long getFinalPrice();
    Long getDepartureTime();
    Long getCreatedAt();
    String getCarName();
    DriverInfo getDriverInfo();
    Long getOrderProgress();
    String getPhoneNumber();
    ClientRate getClientRate();
}
