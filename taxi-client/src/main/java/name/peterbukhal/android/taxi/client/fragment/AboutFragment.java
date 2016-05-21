package name.peterbukhal.android.taxi.client.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import name.peterbukhal.android.taxi.client.R;

/**
 * Created by
 *      petronic on 17.05.16.
 */
public final class AboutFragment extends Fragment {

    public static final String FRAGMENT_TAG_ABOUT = "fragment_tag_about";

    public static Fragment newInstance() {
        Bundle arguments = new Bundle();

        Fragment fragment = new AboutFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private WebView mWebView;
    private ProgressBar mProgressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.f_about, container, false);

        if (contentView != null) {
            mWebView = (WebView) contentView.findViewById(R.id.browser);
            mWebView.getSettings().setJavaScriptEnabled(true);

            mProgressBar = (ProgressBar) contentView.findViewById(R.id.progressBar);
        }

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                mWebView.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
            }

        });
        mWebView.loadUrl("http://ya.ru/");
    }

}
