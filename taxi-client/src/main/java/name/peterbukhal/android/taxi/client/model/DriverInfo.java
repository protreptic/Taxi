package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.unused.Nullable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface DriverInfo extends Parcelable, Nullable {
    Long getId();
    String getName();
    Float getRating();
    Long getRatingCount();
    String getPhoneNumber();
    CarInfo getCarInfo();
}
