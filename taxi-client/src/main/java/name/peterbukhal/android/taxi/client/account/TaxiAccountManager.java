package name.peterbukhal.android.taxi.client.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.taxi.client.R;

/**
 * Created by
 *      petronic on 11.05.16.
 */
public final class TaxiAccountManager {

    public static final String EXTRA_ACCOUNT = "extra_account";
    public static final String EXTRA_ACCOUNT_TOKEN = "extra_account_token";
    public static final String EXTRA_ACCOUNT_GCM_TOKEN = "extra_account_gcm_token";

    private static TaxiAccountManager sInstance;

    private final Context mContext;
    private final AccountManager mAccountManager;
    private List<Account> mAccounts;

    @SuppressWarnings("unused")
    private TaxiAccountManager() {
        mContext = null;
        mAccountManager = null;
        mAccounts = Collections.emptyList();
    }

    private TaxiAccountManager(Context context) {
        mContext = context;
        mAccountManager = AccountManager.get(context);
        mAccounts = getAccounts();
    }

    public void addAccount(Activity activity, AccountManagerCallback<Bundle> callback) {
        mAccountManager.addAccount(TaxiClientAccount.ACCOUNT_TYPE, null, null, null, activity, callback, null);
    }

    public void addAccountExplicitly(Account account, String password) {
        mAccountManager.addAccountExplicitly(account, password, Bundle.EMPTY);
    }

    public String peekAuthToken(Account account) {
        return mAccountManager.peekAuthToken(account, TaxiClientAccount.ACCOUNT_TYPE);
    }

    public void invalidateToken(String token) {
        mAccountManager.invalidateAuthToken(TaxiClientAccount.ACCOUNT_TYPE, token);
    }

    public void getToken(Account account, AccountManagerCallback<Bundle> callback) {
        mAccountManager.getAuthToken(account, TaxiClientAccount.ACCOUNT_TYPE, Bundle.EMPTY, true, callback, null);
    }

    public void setToken(Account account, String token) {
        mAccountManager.setAuthToken(account, TaxiClientAccount.ACCOUNT_TYPE, token);
    }

    public String getGcmToken(Account account) {
        return mAccountManager.getUserData(account, EXTRA_ACCOUNT_GCM_TOKEN);
    }

    public void setGcmToken(Account account, String gcmToken) {
        mAccountManager.setUserData(account, EXTRA_ACCOUNT_GCM_TOKEN, gcmToken);
    }

    public boolean isExists(Account account) {
        return mAccounts.contains(account);
    }

    public void setPassword(Account account, String password) {
        mAccountManager.setPassword(account, password);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(
                Arrays.asList(mAccountManager.getAccountsByType(TaxiClientAccount.ACCOUNT_TYPE)));
    }

    public PickUpAccountAdapter getAccountAdapter() {
        return new PickUpAccountAdapter(mContext, mAccounts);
    }

    private static final Object LOCK_OBJECT = new Object();

    public static TaxiAccountManager get(Context context) {
        synchronized (LOCK_OBJECT) {
            if (sInstance == null) {
                sInstance = new TaxiAccountManager(context);
            }
        }

        return sInstance;
    }

    public static class PickUpAccountAdapter extends BaseAdapter {

        public static final int ADD_ACCOUNT_ITEM_ID = Integer.MAX_VALUE;

        private final Context mContext;
        private final List<Account> mAccounts;

        public PickUpAccountAdapter(Context context, List<Account> accounts) {
            mContext = context;
            mAccounts = Collections.unmodifiableList(accounts);
        }

        @Override
        public int getCount() {
            return mAccounts.size() + 1;
        }

        @Override
        public Account getItem(int position) {
            return mAccounts.get(position);
        }

        @Override
        public long getItemId(int position) {
            if (position == mAccounts.size()) {
                return ADD_ACCOUNT_ITEM_ID;
            } else {
                return mAccounts.get(position).hashCode();
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AccountHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.l_account, parent, false);

                holder = new AccountHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (AccountHolder) convertView.getTag();
            }

            if (position == mAccounts.size()) {
                holder.name.setText(R.string.add_account);
                holder.type.setText(R.string.add_account_description);
            } else {
                Account account = mAccounts.get(position);

                holder.name.setText(account.name);
                holder.type.setText(account.type);
            }

            return convertView;
        }

        class AccountHolder {

            TextView name;
            TextView type;

            AccountHolder(View view) {
                name = (TextView) view.findViewById(R.id.account_name);
                type = (TextView) view.findViewById(R.id.account_type);
            }

        }

    }

}
