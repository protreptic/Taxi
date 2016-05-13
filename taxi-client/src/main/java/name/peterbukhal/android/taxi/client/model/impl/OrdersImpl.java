package name.peterbukhal.android.taxi.client.model.impl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.Orders;

/**
 * Created by
 * petronic on 07.05.16.
 */
public class OrdersImpl implements Orders {

    private List<Order> orders = Collections.emptyList();

    public OrdersImpl(List<Order> orders) {
        this.orders = Collections.unmodifiableList(orders);
    }

    protected OrdersImpl(Parcel parcel) {
        this.orders = Collections.unmodifiableList(parcel.createTypedArrayList(OrderImpl.CREATOR));
    }

    public static final Parcelable.Creator<Orders> CREATOR = new Parcelable.Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new OrdersImpl(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public Integer getTotalCount() {
        return orders.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(orders);
    }

}
