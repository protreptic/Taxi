package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface ClientRate extends Parcelable {

    Long getRate();
    String getComment();
    Boolean isNull();

}
