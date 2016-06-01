package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.unused.Nullable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface Point extends Parcelable, Nullable {
    Double getLatitude();
    Double getLongitude();
    Boolean isNull();
    Boolean isZero();
}
