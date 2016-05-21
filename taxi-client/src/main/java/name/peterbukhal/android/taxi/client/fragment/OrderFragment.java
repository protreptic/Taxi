package name.peterbukhal.android.taxi.client.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.activity.MainActivity;
import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.impl.nil.NullOrder;

/**
 * Created by
 *      petronic on 03.04.16.
 */
public final class OrderFragment extends Fragment {

    public static final String FRAGMENT_TAG_ORDER = "fragment_tag_order";
    public static final String ARG_ORDER = "arg_order";

    public static Fragment newInstance(Order order) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_ORDER, order);

        Fragment fragment = new OrderFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private TextView tvOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.f_order, container, false);

        if (contentView != null) {
            tvOrder = (TextView) contentView.findViewById(R.id.order);
        }

        return contentView;
    }

    private Order mOrder;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(ARG_ORDER, mOrder);
    }

    private MainActivity mActivity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mActivity = (MainActivity) getActivity();

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_ORDER)) {
            mOrder = savedInstanceState.getParcelable(ARG_ORDER);
        } else if (getArguments() != null && getArguments().containsKey(ARG_ORDER)) {
            mOrder = getArguments().getParcelable(ARG_ORDER);
        } else {
            mOrder = new NullOrder();
        }

        if (mOrder.isNull()) {
            tvOrder.setText(String.format(new Locale("ru", "RU"), "%d", mOrder.getId()));
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mActivity.showBackArrow();
    }

    @Override
    public void onStop() {
        super.onStop();

        mActivity.showHamburger();
    }
}
