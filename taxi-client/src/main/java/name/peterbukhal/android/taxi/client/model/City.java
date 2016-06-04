package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface City extends Parcelable, Nullable {
    Long getId();
    String getName();
    String getApiUrl();
    Point getPoint();
    SpnPoint getSpnPoint();
    Boolean getTransfers();
    String[] getInAppPayMethods();
    Long getExperimentalEconomPlus();
}
