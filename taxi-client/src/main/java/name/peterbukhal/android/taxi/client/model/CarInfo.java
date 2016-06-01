package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.unused.Nullable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface CarInfo extends Parcelable, Nullable {
    String getName();
    String getColor();
    Long getCategory();
    String getNumber();
}
