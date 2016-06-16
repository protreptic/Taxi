package name.peterbukhal.android.taxi.partner.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by
 *      petronic on 16.06.16.
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
