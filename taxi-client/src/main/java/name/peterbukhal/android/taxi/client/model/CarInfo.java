package name.peterbukhal.android.taxi.client.model;

import android.os.Parcelable;

/**
 * Created by
 *      petronic on 25.03.16.
 */
public interface CarInfo extends Parcelable {

    String getName();
    String getColor();
    Long getCategory();
    String getNumber();
    Boolean isNull();

}
