package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.unused.Nullable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface RoutePoint extends Parcelable, Nullable {
    Long getType();
    String getName();
    String getAddress();
    Point getPoint();
}
