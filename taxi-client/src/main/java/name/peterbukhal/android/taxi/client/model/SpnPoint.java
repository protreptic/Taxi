package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public interface SpnPoint extends Parcelable, Nullable {
    Double getSpnLatitude();
    Double getSpnLongitude();
    Boolean isZero();
}
