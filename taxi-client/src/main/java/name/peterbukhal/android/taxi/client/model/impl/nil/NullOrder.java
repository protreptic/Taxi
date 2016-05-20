package name.peterbukhal.android.taxi.client.model.impl.nil;

import android.annotation.SuppressLint;
import android.os.Parcel;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.ClientRate;
import name.peterbukhal.android.taxi.client.model.DriverInfo;
import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.RoutePoint;

/**
 * Created by
 *      petronic on 18.05.16.
 */
@SuppressLint("ParcelCreator")
public final class NullOrder implements Order {

    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public List<RoutePoint> getRoutePoints() {
        return Collections.emptyList();
    }

    @Override
    public Long getApproximatePrice() {
        return 0L;
    }

    @Override
    public Long getFixedPrice() {
        return 0L;
    }

    @Override
    public Long getFinalPrice() {
        return 0L;
    }

    @Override
    public Long getDepartureTime() {
        return 0L;
    }

    @Override
    public Long getCreatedAt() {
        return 0L;
    }

    @Override
    public String getCarName() {
        return "";
    }

    @Override
    public DriverInfo getDriverInfo() {
        return new NullDriverInfo();
    }

    @Override
    public ProgressState getOrderProgress() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return "";
    }

    @Override
    public ClientRate getClientRate() {
        return new NullClientRate();
    }

    @Override
    public Boolean isActive() {
        return false;
    }

    @Override
    public Boolean isNull() {
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
