package name.peterbukhal.android.ordersfragmentlab.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by
 * petronic on 25.03.16.
 */
public interface Order extends Parcelable {

    enum ProgressState {

        NOT_ACCEPTED(1),
        ACCEPTED(2),
        MOVING_TO_CLIENT(3),
        WAITING_FOR_CLIENT(4),
        IN_PROGRESS(5),
        DONE(6),
        CANCELED(7),
        FAILED(8),
        WAITING_FOR_PAYMENT(9);

        private int value;

        ProgressState(int value) {
            this.value = value;
        }

        public static ProgressState valueOf(int value) {
            return ProgressState.NOT_ACCEPTED;
        }

    }

    Long getId();
    List<RoutePoint> getRoutePoints();
    Long getApproximatePrice();
    Long getFixedPrice();
    Long getFinalPrice();
    Long getDepartureTime();
    Long getCreatedAt();
    String getCarName();
    DriverInfo getDriverInfo();
    ProgressState getOrderProgress();
    String getPhoneNumber();
    ClientRate getClientRate();

}
