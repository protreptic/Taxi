package name.peterbukhal.android.ordersfragmentlab.account;

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

import name.peterbukhal.android.ordersfragmentlab.R;

/**
 * Created by
 * petronic on 11.05.16.
 */
public class TaxikAccountManager {

    public static final String EXTRA_ACCOUNT = "extra_account";
    public static final String EXTRA_ACCOUNT_TOKEN = "extra_account_token";

    private static TaxikAccountManager sInstance;

    private final Context mContext;
    private final AccountManager mAccountManager;
    private List<Account> mAccounts;

    @SuppressWarnings("unused")
    private TaxikAccountManager() {
        mContext = null;
        mAccountManager = null;
        mAccounts = Collections.emptyList();
    }

    private TaxikAccountManager(Context context) {
        mContext = context;
        mAccountManager = AccountManager.get(context);
        mAccounts = getAccounts();
    }

    public void addAccount(Activity activity, AccountManagerCallback<Bundle> callback) {
        mAccountManager.addAccount(TaxikAccount.ACCOUNT_TYPE, null, null, null, activity, callback, null);
    }

    public void addAccountExplicitly(Account account, String password) {
        mAccountManager.addAccountExplicitly(account, password, Bundle.EMPTY);
    }

    public String peekAuthToken(Account account) {
        return mAccountManager.peekAuthToken(account, TaxikAccount.ACCOUNT_TYPE);
    }

    public void invalidateAuthToken(String token) {
        mAccountManager.invalidateAuthToken(TaxikAccount.ACCOUNT_TYPE, token);
    }

    public void getAuthToken(Account account, AccountManagerCallback<Bundle> callback) {
        mAccountManager.getAuthToken(account, TaxikAccount.ACCOUNT_TYPE, Bundle.EMPTY, true, callback, null);
    }

    public void setAuthToken(Account account, String token) {
        mAccountManager.setAuthToken(account, TaxikAccount.ACCOUNT_TYPE, token);
    }

    public boolean isExists(Account account) {
        return mAccounts.contains(account);
    }

    public void setPassword(Account account, String password) {
        mAccountManager.setPassword(account, password);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(
                Arrays.asList(mAccountManager.getAccountsByType(TaxikAccount.ACCOUNT_TYPE)));
    }

    public PickUpAccountAdapter getAccountAdapter() {
        return new PickUpAccountAdapter(mContext, mAccounts);
    }

    @SuppressWarnings({"unused", "deprecation"})
    private void removeAccount(Account account, AccountManagerCallback<Boolean> callback) {
        mAccountManager.removeAccount(account, callback, null);
    }

    private static final Object mLock = new Object();

    public static TaxikAccountManager get(Context context) {
        synchronized (mLock) {
            if (sInstance == null) {
                sInstance = new TaxikAccountManager(context);
            }
        }

        return sInstance;
    }

    public static class PickUpAccountAdapter extends BaseAdapter {

        public static final int ADD_ACCOUNT_ITEM_ID = 761263192;

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
                holder.name.setText(R.string.new_user);
                holder.type.setText(R.string.new_user_description);
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
