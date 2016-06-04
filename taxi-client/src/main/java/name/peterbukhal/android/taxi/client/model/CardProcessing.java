package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

/**
 * Created by
 *      petronic on 19.05.16.
 */
public interface CardProcessing extends Parcelable, Nullable {
    Long getId();
    Boolean getManualPayment();
}
