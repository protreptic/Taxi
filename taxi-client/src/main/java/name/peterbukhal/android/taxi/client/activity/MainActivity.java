package name.peterbukhal.android.taxi.client.activity;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.service.OrderMonitoringService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmListenerService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmMessageBroadcastReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationBroadcastReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationService;
import name.peterbukhal.android.taxi.client.service.ntp.NtpService;

import static name.peterbukhal.android.taxi.client.account.TaxiAccountManager.EXTRA_ACCOUNT;
import static name.peterbukhal.android.taxi.client.account.TaxiClientAccount.ACCOUNT_AUTHORITY;

public class MainActivity extends TaxiActivity {

    private LocalBroadcastManager mBroadcastManager;
    private Account mAccount;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(EXTRA_ACCOUNT, mAccount);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());

        if (savedInstanceState != null
                && savedInstanceState.containsKey(EXTRA_ACCOUNT)) {
            mAccount = savedInstanceState.getParcelable(EXTRA_ACCOUNT);
        } else {
            mAccount = getIntent().getParcelableExtra(EXTRA_ACCOUNT);
        }
    }

    @SuppressWarnings("unused")
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

        /**
         * Start NTP service.
         */
        startService(new Intent(getApplicationContext(), NtpService.class));

        Intent intent = new Intent(getApplicationContext(), OrderMonitoringService.class);
        intent.putExtra(TaxiAccountManager.EXTRA_ACCOUNT, mAccount);

        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mBroadcastManager.unregisterReceiver(gcmRegistrationReceiver);
        mBroadcastManager.unregisterReceiver(gcmNewMessageReceiver);

        stopService(new Intent(getApplicationContext(), NtpService.class));
        stopService(new Intent(getApplicationContext(), OrderMonitoringService.class));
    }

}
