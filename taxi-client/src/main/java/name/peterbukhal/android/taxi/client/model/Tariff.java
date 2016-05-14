package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

/**
 * Created by
 * petronic on 25.03.16.
 */
public interface Tariff extends Parcelable {

    Long getId();
    String getName();
    Long getRate();
    Long getOrder();

}
