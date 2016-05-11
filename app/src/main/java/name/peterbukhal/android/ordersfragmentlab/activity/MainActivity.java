package name.peterbukhal.android.ordersfragmentlab.activity;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import name.peterbukhal.android.ordersfragmentlab.R;
import name.peterbukhal.android.ordersfragmentlab.account.TaxikAccountManager;
import name.peterbukhal.android.ordersfragmentlab.fragment.OrdersFragment;
import name.peterbukhal.android.ordersfragmentlab.fragment.UserOrdersFragment;
import name.peterbukhal.android.ordersfragmentlab.service.OrderStateMonitoringService;
import name.peterbukhal.android.ordersfragmentlab.service.gcm.TaxikGcmListenerService;
import name.peterbukhal.android.ordersfragmentlab.service.gcm.TaxikGcmRegistrationIntentService;

import static name.peterbukhal.android.ordersfragmentlab.account.TaxikAccount.ACCOUNT_AUTHORITY;

public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager mBroadcastManager;
    private Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_main);

        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        mAccount = getIntent().getParcelableExtra(TaxikAccountManager.EXTRA_ACCOUNT);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContent, UserOrdersFragment.newInstance(), OrdersFragment.FRAGMENT_TAG_ORDERS)
                    .commit();
        }
    }

    private void requestSync() {
        ContentResolver.requestSync(mAccount, ACCOUNT_AUTHORITY, Bundle.EMPTY);
    }

    private BroadcastReceiver gcmRegistrationReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(TaxikGcmRegistrationIntentService.ACTION_GCM_REGISTRATION)) {
                getSharedPreferences("main", MODE_PRIVATE)
                        .edit()
                        .putString("gcm_token", intent.getStringExtra(TaxikGcmRegistrationIntentService.EXTRA_GCM_TOKEN))
                        .commit();
            }
        }

    };

    private BroadcastReceiver gcmNewMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(TaxikGcmListenerService.ACTION_GCM_NEW_MESSAGE)) {
                Toast.makeText(getApplicationContext(), "New message received", Toast.LENGTH_LONG).show();
            }
        }

    };

    @Override
    protected void onStart() {
        super.onStart();

        startService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));

        mBroadcastManager.registerReceiver(gcmRegistrationReceiver,
                new IntentFilter(TaxikGcmRegistrationIntentService.ACTION_GCM_REGISTRATION));
        mBroadcastManager.registerReceiver(gcmNewMessageReceiver,
                new IntentFilter(TaxikGcmListenerService.ACTION_GCM_NEW_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));

        mBroadcastManager.unregisterReceiver(gcmRegistrationReceiver);
        mBroadcastManager.unregisterReceiver(gcmNewMessageReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add("Make a sync");
        item.setIcon(android.R.drawable.stat_notify_sync);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                requestSync();

                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

}
