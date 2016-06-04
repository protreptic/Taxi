package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import name.peterbukhal.android.taxi.client.model.nil.NullTariff;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface Tariff extends Parcelable, Nullable {

    NullTariff NULL = new NullTariff();

    Long getId();
    String getName();
    Long getRate();
    Long getOrder();
}
