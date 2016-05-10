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
            switch (value) {
                case 1: return NOT_ACCEPTED;
                case 2: return ACCEPTED;
                case 3: return MOVING_TO_CLIENT;
                case 4: return WAITING_FOR_CLIENT;
                case 5: return IN_PROGRESS;
                case 6: return DONE;
                case 7: return CANCELED;
                case 8: return FAILED;
                case 9: return WAITING_FOR_PAYMENT;
                default: throw new RuntimeException("Unknown order state");
            }
        }

        public int getValue() {
            return value;
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

    Boolean isActive();

}
