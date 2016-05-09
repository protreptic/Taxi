package name.peterbukhal.android.ordersfragmentlab.unused;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import name.peterbukhal.android.ordersfragmentlab.R;
import name.peterbukhal.android.ordersfragmentlab.fragments.OrdersFragment;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.QueryOrdersRequest.OrderType;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.fragment_user_orders, container, false);

        if (contentView != null) {
            viewPager = (ViewPager) contentView.findViewById(R.id.fragment_user_orders_pager);
            tabLayout = (TabLayout) contentView.findViewById(R.id.fragment_user_orders_pager_tabs);
        }

        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();

        UserOrdersPagerAdapter adapter = new UserOrdersPagerAdapter();

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class UserOrdersPagerAdapter extends FragmentPagerAdapter {

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
