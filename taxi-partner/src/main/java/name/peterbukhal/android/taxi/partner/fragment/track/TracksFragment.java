package name.peterbukhal.android.taxi.partner.fragment.track;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class TracksFragment extends Fragment {

    public static final String FRAGMENT_TAG_TRACKS = "fragment_tag_tracks";

    public static Fragment newInstance() {
        Bundle arguments = new Bundle();

        Fragment fragment = new TracksFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }



}
