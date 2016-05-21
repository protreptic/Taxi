package name.peterbukhal.android.taxi.client.fragment;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrdersRequest.OrderType;

/**
 * Created by
 *      petronic on 03.04.16.
 */
public final class UserOrdersFragment extends Fragment {

    public static final String FRAGMENT_TAG_USER_ORDERS = "fragment_tag_user_orders";
    public static final String ARG_ACCOUNT = "arg_account";

    public static Fragment newInstance(Account account) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_ACCOUNT, account);

        Fragment fragment = new UserOrdersFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(false);
    }

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.f_user_orders, container, false);

        if (contentView != null) {
            mViewPager = (ViewPager) contentView.findViewById(R.id.fragment_user_orders_pager);
            mViewPager.setOffscreenPageLimit(2);

            mTabLayout = (TabLayout) contentView.findViewById(R.id.fragment_user_orders_pager_tabs);
        }

        return contentView;
    }

    private Account mAccount;
    private UserOrdersPagerAdapter mAdapter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(ARG_ACCOUNT, mAccount);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_ACCOUNT)) {
            mAdapter = new UserOrdersPagerAdapter(getActivity(), getChildFragmentManager(), mAccount);
        } else if (getArguments() != null && getArguments().containsKey(ARG_ACCOUNT)) {
            mAccount = getArguments().getParcelable(ARG_ACCOUNT);
            mAdapter = new UserOrdersPagerAdapter(getActivity(), getChildFragmentManager(), mAccount);
        }

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public static class UserOrdersPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;
        private Account mAccount;

        public UserOrdersPagerAdapter(Context context, FragmentManager fragmentManager, Account account) {
            super(fragmentManager);

            mContext = context;
            mAccount = account;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return OrdersFragment.newInstance(mAccount, OrderType.ACTIVE);
                case 1:
                    return OrdersFragment.newInstance(mAccount, OrderType.CANCELED);
                case 2:
                    return OrdersFragment.newInstance(mAccount, OrderType.FINISHED);
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
                    pageTitle = mContext.getString(R.string.active_orders);
                } break;
                case 1: {
                    pageTitle = mContext.getString(R.string.canceled_orders);
                } break;
                case 2: {
                    pageTitle = mContext.getString(R.string.finished_orders);
                } break;
            }

            return pageTitle;
        }
    }

}
