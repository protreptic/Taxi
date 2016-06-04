package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public interface Orders extends Parcelable, Nullable {
    List<Order> getOrders();
    Integer getTotalCount();
}
