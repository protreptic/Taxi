package name.peterbukhal.android.ordersfragmentlab.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class Orders {

    private List<Order> orders;
    private Long totalCount;

    public Orders(List<Order> orders, Long totalCount) {
        this.orders = new ArrayList<>(orders);
        this.totalCount = totalCount;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public Long getTotalCount() {
        return totalCount;
    }

}
