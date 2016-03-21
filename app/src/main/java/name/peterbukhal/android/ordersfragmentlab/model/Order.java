package name.peterbukhal.android.ordersfragmentlab.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class Order implements Parcelable {

    private Long id;

    public Order() {}

    public Order(Long id) {
        this.id = id;
    }

    protected Order(Parcel in) {
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
