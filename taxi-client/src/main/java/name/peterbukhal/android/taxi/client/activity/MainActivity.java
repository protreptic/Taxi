package name.peterbukhal.android.taxi.client.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.service.OrderMonitoringService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmListenerService;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmMessageReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmRegistrationService;
import name.peterbukhal.android.taxi.client.service.ntp.NtpService;

public final class MainActivity extends TaxiBaseActivity {

    private final BroadcastReceiver mGcmRegistrationReceiver = new TaxiGcmRegistrationReceiver();
    private final BroadcastReceiver mGcmMessageReceiver = new TaxiGcmMessageReceiver();

    @Override
    protected void onStart() {
        super.onStart();

        mBroadcastManager.registerReceiver(mGcmRegistrationReceiver,
                new IntentFilter(TaxiGcmRegistrationService.ACTION_GCM_REGISTRATION));
        mBroadcastManager.registerReceiver(mGcmMessageReceiver,
                new IntentFilter(TaxiGcmListenerService.ACTION_GCM_MESSAGE_RECEIVED));

        startService(new Intent(getApplicationContext(), NtpService.class));

        if (TextUtils.isEmpty(mAccountManager.getGcmToken(mAccount))) {
            startService(new Intent(getApplicationContext(), TaxiGcmRegistrationService.class)
                    .putExtra(TaxiAccountManager.EXTRA_ACCOUNT, mAccount));
        }

        startService(new Intent(getApplicationContext(), OrderMonitoringService.class)
                .putExtra(TaxiAccountManager.EXTRA_ACCOUNT, mAccount));

        requestSync();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mBroadcastManager.unregisterReceiver(mGcmRegistrationReceiver);
        mBroadcastManager.unregisterReceiver(mGcmMessageReceiver);

        stopService(new Intent(getApplicationContext(), NtpService.class));
        stopService(new Intent(getApplicationContext(), OrderMonitoringService.class));
    }

}
