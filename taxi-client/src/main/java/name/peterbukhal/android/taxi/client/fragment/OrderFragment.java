package name.peterbukhal.android.taxi.client.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.model.Order;

/**
 * Created by
 * petronic on 03.04.16.
 */
public class OrderFragment extends Fragment {

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_ORDER)) {
            mOrder = savedInstanceState.getParcelable(ARG_ORDER);
        } else if (getArguments() != null && getArguments().containsKey(ARG_ORDER)) {
            mOrder = getArguments().getParcelable(ARG_ORDER);
        }

        if (mOrder != null) {
            tvOrder.setText(mOrder.toString());
        }
    }

}
