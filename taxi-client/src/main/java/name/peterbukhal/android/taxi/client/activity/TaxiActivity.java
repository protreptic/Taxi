package name.peterbukhal.android.taxi.client.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.account.TaxiClientAccount;
import name.peterbukhal.android.taxi.client.fragment.AboutFragment;
import name.peterbukhal.android.taxi.client.fragment.CreateOrderFragment;
import name.peterbukhal.android.taxi.client.fragment.UserOrdersFragment;

import static name.peterbukhal.android.taxi.client.account.TaxiAccountManager.EXTRA_ACCOUNT;
import static name.peterbukhal.android.taxi.client.account.TaxiClientAccount.ACCOUNT_AUTHORITY;
import static name.peterbukhal.android.taxi.client.fragment.AboutFragment.FRAGMENT_TAG_ABOUT;
import static name.peterbukhal.android.taxi.client.fragment.CreateOrderFragment.FRAGMENT_TAG_CREATE_ORDER;
import static name.peterbukhal.android.taxi.client.fragment.UserOrdersFragment.FRAGMENT_TAG_USER_ORDERS;

/**
 * Created by
 * petronic on 15.05.16.
 */
public abstract class TaxiActivity extends AppCompatActivity {

    protected LocalBroadcastManager mBroadcastManager;
    private FragmentManager mFragmentManager;
    private Drawer mDrawer;
    protected TaxiAccountManager mAccountManager;
    protected Account mAccount;
    private ActionBar mActionBar;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(EXTRA_ACCOUNT, mAccount);
        outState.putLong("111", mSelectedItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_main);

        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        mFragmentManager = getSupportFragmentManager();
        mAccountManager = TaxiAccountManager.get(getApplicationContext());
        mAccount = getIntent().getParcelableExtra(TaxiAccountManager.EXTRA_ACCOUNT);
        mAccountManager.setDefaultAccount(mAccount);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSavedInstance(savedInstanceState)
                .withHasStableIds(true)
                .withDrawerItems(generateDrawerItems())
                .withOnDrawerItemClickListener(mDrawerItemClickListener)
                .withAccountHeader(generateAccountHeader())
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .build();

        if (savedInstanceState != null && savedInstanceState.containsKey("111")) {
            mDrawer.setSelection(savedInstanceState.getLong("111"), false);
        } else {
            mDrawer.setSelection(MENU_ITEM_CREATE_ORDER, true);
        }
    }

    private long mSelectedItem;

    @SuppressWarnings("unused")
    private void requestSync() {
        ContentResolver.requestSync(mAccount, ACCOUNT_AUTHORITY, Bundle.EMPTY);
    }

    public void showBackArrow(Drawer.OnDrawerNavigationListener listener) {
        mDrawer
                .getActionBarDrawerToggle()
                .setDrawerIndicatorEnabled(false);
        mDrawer
                .setOnDrawerNavigationListener(listener);
        mActionBar
                .setDisplayHomeAsUpEnabled(true);
    }

    public void showHamburger() {
        mActionBar
                .setDisplayHomeAsUpEnabled(false);
        mDrawer
                .setOnDrawerNavigationListener(null);
        mDrawer
                .getActionBarDrawerToggle()
                .setDrawerIndicatorEnabled(true);
    }

    private static final int MENU_ITEM_CREATE_ORDER = 41231;
    private static final int MENU_ITEM_ORDERS = 41232;
    private static final int MENU_ITEM_ABOUT = 41233;

    private List<IDrawerItem> generateDrawerItems() {
        List<IDrawerItem> items = new ArrayList<>();

        items.add(new PrimaryDrawerItem()
                .withName("Создать заказ".toUpperCase())
                .withIdentifier(MENU_ITEM_CREATE_ORDER)
                .withIcon(android.R.drawable.ic_menu_add));

        items.add(new PrimaryDrawerItem()
                .withName("Заказы".toUpperCase())
                .withIdentifier(MENU_ITEM_ORDERS)
                .withIcon(android.R.drawable.ic_menu_agenda));

        items.add(new PrimaryDrawerItem()
                .withName("О приложении".toUpperCase())
                .withIdentifier(MENU_ITEM_ABOUT)
                .withIcon(android.R.drawable.ic_menu_agenda));

        return items;
    }

    private Drawer.OnDrawerItemClickListener mDrawerItemClickListener = new Drawer.OnDrawerItemClickListener() {

        @Override
        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            Integer identifier = (int) drawerItem.getIdentifier();
            mSelectedItem = identifier;

            switch (identifier) {
                case MENU_ITEM_CREATE_ORDER: {
                    if (mFragmentManager.findFragmentByTag(FRAGMENT_TAG_CREATE_ORDER) != null)
                        break;

                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_content,
                                    CreateOrderFragment.newInstance(mAccount),
                                    FRAGMENT_TAG_CREATE_ORDER)
                            .commit();
                }
                break;
                case MENU_ITEM_ORDERS: {
                    if (mFragmentManager.findFragmentByTag(FRAGMENT_TAG_USER_ORDERS) != null) break;

                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_content,
                                    UserOrdersFragment.newInstance(mAccount),
                                    FRAGMENT_TAG_USER_ORDERS)
                            .commit();
                }
                break;
                case MENU_ITEM_ABOUT: {
                    if (mFragmentManager.findFragmentByTag(FRAGMENT_TAG_ABOUT) != null) break;

                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_content,
                                    AboutFragment.newInstance(),
                                    FRAGMENT_TAG_ABOUT)
                            .commit();
                }
                break;
                default: {
                    Toast.makeText(getApplicationContext(), drawerItem.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            }

            return false;
        }

    };

    private static final int ADD_ACCOUNT_ID = 100000;
    private static final int MANAGE_ACCOUNTS = 100001;

    private void runApplication(Account account) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra(EXTRA_ACCOUNT, account);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

    private AccountHeader generateAccountHeader() {
        List<IProfile> profiles = new ArrayList<>();

        for (Account account : mAccountManager.getAccounts()) {
            profiles.add(new ProfileDrawerItem()
                    //.withName("")
                    .withEmail(account.name)
                    .withIdentifier(account.hashCode())
                    .withTag(account));
        }

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.color.colorPrimaryDark)
                .withProfiles(profiles)
                .addProfiles(
                        new ProfileSettingDrawerItem()
                                .withName("Add account")
                                .withIcon(android.R.drawable.ic_menu_add)
                                .withIdentifier(ADD_ACCOUNT_ID),
                        new ProfileSettingDrawerItem()
                                .withName("Manage accounts")
                                .withIcon(android.R.drawable.ic_menu_preferences)
                                .withIdentifier(MANAGE_ACCOUNTS)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int) profile.getIdentifier()) {
                            case ADD_ACCOUNT_ID: {
                                mAccountManager.addAccount(TaxiActivity.this, new AccountManagerCallback<Bundle>() {

                                    @Override
                                    public void run(AccountManagerFuture<Bundle> future) {
                                        //noinspection TryWithIdenticalCatches,EmptyCatchBlock
                                        try {
                                            Bundle bundle = future.getResult();

                                            String accountName = bundle.getString(AccountManager.KEY_ACCOUNT_NAME);
                                            //String accountType = bundle.getString(AccountManager.KEY_ACCOUNT_TYPE);

                                            Toast.makeText(getApplicationContext(), accountName, Toast.LENGTH_SHORT).show();
                                        } catch (OperationCanceledException e) {

                                        } catch (AuthenticatorException e) {

                                        } catch (IOException e) {

                                        }
                                    }

                                });
                            }
                            break;
                            case MANAGE_ACCOUNTS: {
                                Intent intent = new Intent(Settings.ACTION_SYNC_SETTINGS);
                                intent.putExtra(Settings.EXTRA_AUTHORITIES, new String[]{
                                        TaxiClientAccount.ACCOUNT_AUTHORITY
                                });

                                startActivity(intent);
                            }
                            break;
                            default: {
                                ProfileDrawerItem profileDrawerItem = (ProfileDrawerItem) profile;
                                Account account = (Account) profileDrawerItem.getTag();

                                if (!account.equals(mAccount)) {
                                    runApplication(account);
                                }
                            }
                        }

                        return false;
                    }
                })
                .build();

        accountHeader.setActiveProfile(mAccount.hashCode());

        return accountHeader;
    }

    private boolean isBackPressedOnce;

    @Override
    public void onBackPressed() {
        /**
         *   Если боковое меню открыто, то закрыть его при нажатии на стрелку назад.
         */
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
            return;
        }

        /**
         *   Если пользователь нажал на стрелку назад, то ждем повторного нажатия на
         *   стрелку прежде как выходим из приложения и выводим сообщение, то что,
         *   для закрытия приложения нужно нажать на стрелку еще раз.
         */
        if (isBackPressedOnce) {
            isBackPressedOnce = false;

            super.onBackPressed();
        } else {
            isBackPressedOnce = true;

            Executors
                    .newSingleThreadScheduledExecutor()
                    .schedule(new Runnable() {
                        @Override
                        public void run() {
                            isBackPressedOnce = false;
                        }
                    }, 2, TimeUnit.SECONDS);

            Toast.makeText(getApplicationContext(), R.string.app_exit,
                    Toast.LENGTH_SHORT).show();
        }
    }

}
