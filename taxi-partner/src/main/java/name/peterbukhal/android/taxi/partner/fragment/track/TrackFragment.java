package name.peterbukhal.android.taxi.partner.fragment.track;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class TrackFragment extends Fragment {

    public static final String FRAGMENT_TAG_TRACK = "fragment_tag_track";

    public static Fragment newInstance() {
        Bundle arguments = new Bundle();

        Fragment fragment = new TrackFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

}
