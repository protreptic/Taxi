package name.peterbukhal.android.ordersfragmentlab.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.ordersfragmentlab.model.impl.OrderImpl;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class Orders implements Parcelable {

    private List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    protected Orders(Parcel in) {
        orders = in.createTypedArrayList(OrderImpl.CREATOR);
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
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
