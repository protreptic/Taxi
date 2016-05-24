package name.peterbukhal.android.taxi.client.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import name.peterbukhal.android.taxi.client.R;

/**
 * Created by
 *      petronic on 22.05.16.
 */
public final class MenuStubFragment extends Fragment {

    public static final String FRAGMENT_TAG_MENU_STUB = "fragment_tag_menu_stub";

    public static final String ARG_ID = "arg_id";
    public static final String ARG_TEXT = "arg_text";

    public static Fragment newInstance(Long id, String text) {
        Bundle arguments = new Bundle();
        arguments.putLong(ARG_ID, id);
        arguments.putString(ARG_TEXT, text);

        Fragment fragment = new MenuStubFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_menu_stub, container, false);
    }
}
