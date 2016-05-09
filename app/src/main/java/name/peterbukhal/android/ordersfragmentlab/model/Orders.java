package name.peterbukhal.android.ordersfragmentlab.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public interface Orders extends Parcelable {
    List<Order> getOrders();
    Integer getTotalCount();
}
