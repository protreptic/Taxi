package name.peterbukhal.android.taxi.partner.taximeter;

import android.os.Parcel;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class DefaultTaximeterContext implements TaximeterContext {

    public DefaultTaximeterContext() {}

    protected DefaultTaximeterContext(Parcel in) {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Creator<DefaultTaximeterContext> CREATOR = new Creator<DefaultTaximeterContext>() {

        @Override
        public DefaultTaximeterContext createFromParcel(Parcel source) {
            return new DefaultTaximeterContext(source);
        }

        @Override
        public DefaultTaximeterContext[] newArray(int size) {
            return new DefaultTaximeterContext[size];
        }

    };
}
