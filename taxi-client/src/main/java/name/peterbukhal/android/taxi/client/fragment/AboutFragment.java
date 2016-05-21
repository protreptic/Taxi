package name.peterbukhal.android.taxi.client.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import name.peterbukhal.android.taxi.client.R;

/**
 * Created by
 *      petronic on 17.05.16.
 */
public final class AboutFragment extends Fragment {

    public static final String FRAGMENT_TAG_ABOUT = "fragment_tag_about";

    public static Fragment newInstance() {
        Bundle arguments = new Bundle();

        Fragment fragment = new OrdersFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mWebView = (WebView) inflater.inflate(R.layout.f_about, container, false);

        return mWebView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWebView.loadUrl("http://www.google.com/");
    }

}
