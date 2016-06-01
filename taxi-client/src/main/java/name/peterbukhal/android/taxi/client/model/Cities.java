package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

import java.util.List;

import name.peterbukhal.android.taxi.client.unused.Nullable;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public interface Cities extends Parcelable, Nullable {
    List<City> getCities();
}
