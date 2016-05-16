package name.peterbukhal.android.taxi.client.activity;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.service.OrderStateMonitoringService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmListenerService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmMessageBroadcastReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationBroadcastReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationService;
import name.peterbukhal.android.taxi.client.service.ntp.NtpService;

import static name.peterbukhal.android.taxi.client.account.TaxiClientAccount.ACCOUNT_AUTHORITY;

public class MainActivity extends TaxiActivity {

    private LocalBroadcastManager mBroadcastManager;
    private Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        mAccount = getIntent().getParcelableExtra(TaxiAccountManager.EXTRA_ACCOUNT);
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

        startService(new Intent(getApplicationContext(), NtpService.class));
        startService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();

        mBroadcastManager.unregisterReceiver(gcmRegistrationReceiver);
        mBroadcastManager.unregisterReceiver(gcmNewMessageReceiver);

        stopService(new Intent(getApplicationContext(), NtpService.class));
        stopService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));
    }

}
