package name.peterbukhal.android.taxi.client.activity;

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

import java.io.IOException;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.fragment.OrdersFragment;
import name.peterbukhal.android.taxi.client.fragment.UserOrdersFragment;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikService;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikServiceImpl;
import name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.RegisterDeviceResponse;
import name.peterbukhal.android.taxi.client.service.OrderStateMonitoringService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmListenerService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmMessageBroadcastReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationBroadcastReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationService;
import retrofit2.Response;

import static name.peterbukhal.android.taxi.client.account.TaxiClientAccount.ACCOUNT_AUTHORITY;
import static name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequest.DevicePlatform.ANDROID;

public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager mBroadcastManager;
    private Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_main);

        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        mAccount = getIntent().getParcelableExtra(TaxiAccountManager.EXTRA_ACCOUNT);

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

    private BroadcastReceiver gcmRegistrationReceiver = new TaxiGcmRegistrationBroadcastReceiver();
    private BroadcastReceiver gcmNewMessageReceiver = new TaxiGcmMessageBroadcastReceiver();

    @Override
    protected void onStart() {
        super.onStart();

        mBroadcastManager.registerReceiver(gcmRegistrationReceiver,
                new IntentFilter(TaxiGcmRegistrationService.ACTION_GCM_REGISTRATION));
        mBroadcastManager.registerReceiver(gcmNewMessageReceiver,
                new IntentFilter(TaxiGcmListenerService.ACTION_GCM_NEW_MESSAGE));

        startService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();

        mBroadcastManager.unregisterReceiver(gcmRegistrationReceiver);
        mBroadcastManager.unregisterReceiver(gcmNewMessageReceiver);

        stopService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));
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
