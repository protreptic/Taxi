package name.peterbukhal.android.taxi.client.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrdersRequest.OrderType;
import name.peterbukhal.android.taxi.client.unused.Fragment1;

/**
 * Created by
 *      petronic on 03.04.16.
 */
public class UserOrdersFragment extends Fragment {

    public static Fragment newInstance() {
        return new UserOrdersFragment();
    }

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.f_user_orders, container, false);

        if (contentView != null) {
            viewPager = (ViewPager) contentView.findViewById(R.id.fragment_user_orders_pager);
            viewPager.setOffscreenPageLimit(2);

            tabLayout = (TabLayout) contentView.findViewById(R.id.fragment_user_orders_pager_tabs);
        }

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager.setAdapter(new UserOrdersPagerAdapter());
        tabLayout.setupWithViewPager(viewPager);
    }

    public class UserOrdersPagerAdapter extends Fragment1 {

        public UserOrdersPagerAdapter() {
            super(getFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return OrdersFragment.newInstance(OrderType.ACTIVE);
                case 1:
                    return OrdersFragment.newInstance(OrderType.CANCELED);
                case 2:
                    return OrdersFragment.newInstance(OrderType.FINISHED);
                default:
                    throw new RuntimeException();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String pageTitle = "";

            switch (position) {
                case 0: {
                    pageTitle = getString(R.string.active_orders);
                } break;
                case 1: {
                    pageTitle = getString(R.string.canceled_orders);
                } break;
                case 2: {
                    pageTitle = getString(R.string.finished_orders);
                } break;
            }

            return pageTitle;
        }
    }

}
