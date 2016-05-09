package name.peterbukhal.android.ordersfragmentlab.model;

import android.os.Parcelable;

/**
 * Created by
 * petronic on 25.03.16.
 */
public interface RoutePoint extends Parcelable {

    Long getType();
    String getName();
    String getAddress();
    Point getPoint();

}
