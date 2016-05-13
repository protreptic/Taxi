package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.ClientRate;
import name.peterbukhal.android.taxi.client.model.DriverInfo;
import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.RoutePoint;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class OrderImpl implements Order {

    private Long id;
    private List<RoutePoint> routePoints = Collections.emptyList();
    private Long approximatePrice;
    private String phoneNumber;
    private ProgressState progressState;

    public OrderImpl() {}

    protected OrderImpl(Parcel in) {
        id = in.readLong();
        //approximatePrice = in.readLong();

        List<RoutePoint> routePoints1 = new ArrayList<>();
        in.readTypedList(routePoints1, RoutePointImpl.CREATOR);

        routePoints = Collections.unmodifiableList(routePoints1);
        progressState = (ProgressState) in.readSerializable();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {

        @Override
        public Order createFromParcel(Parcel in) {
            return new OrderImpl(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }

    };

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<RoutePoint> getRoutePoints() {
        return Collections.unmodifiableList(routePoints);
    }

    public void setRoutePoints(List<RoutePoint> routePoints) {
        this.routePoints = Collections.unmodifiableList(routePoints);
    }

    @Override
    public Long getApproximatePrice() {
        return approximatePrice;
    }

    public void setApproximatePrice(Long approximatePrice) {
        this.approximatePrice = approximatePrice;
    }

    @Override
    public Long getFixedPrice() {
        return null;
    }

    @Override
    public Long getFinalPrice() {
        return null;
    }

    @Override
    public Long getDepartureTime() {
        return null;
    }

    @Override
    public Long getCreatedAt() {
        return null;
    }

    @Override
    public String getCarName() {
        return null;
    }

    @Override
    public DriverInfo getDriverInfo() {
        return null;
    }

    @Override
    public ProgressState getOrderProgress() {
        return progressState;
    }

    public void setProgressState(ProgressState progressState) {
        this.progressState = progressState;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public ClientRate getClientRate() {
        return null;
    }

    @Override
    public Boolean isActive() {
        switch (progressState) {
            case NOT_ACCEPTED:
            case ACCEPTED:
            case MOVING_TO_CLIENT:
            case WAITING_FOR_CLIENT:
            case IN_PROGRESS:
            case WAITING_FOR_PAYMENT:
                return true;
            case DONE:
            case CANCELED:
            case FAILED:
                return false;
            default: throw new RuntimeException("Unknown order state");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        //dest.writeLong(approximatePrice);
        dest.writeTypedList(routePoints);
        dest.writeSerializable(progressState);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!id.equals(order.getId())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 37;

        result = 37 * result + ((int) (id >>> 32));

        return result;
    }

}
