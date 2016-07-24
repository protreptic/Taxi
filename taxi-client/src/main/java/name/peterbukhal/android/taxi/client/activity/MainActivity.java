package name.peterbukhal.android.taxi.client.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.service.OrderMonitoringService;
import name.peterbukhal.android.taxi.client.service.gcm.GcmMessageService;
import name.peterbukhal.android.taxi.client.unused.GcmMessageReceiver;
import name.peterbukhal.android.taxi.client.unused.GcmRegistrationReceiver;
import name.peterbukhal.android.taxi.client.service.gcm.GcmRegistrationService;
import name.peterbukhal.android.taxi.client.service.ntp.NtpService;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class MainActivity extends BaseActivity {

    private final BroadcastReceiver mGcmRegistrationReceiver = new GcmRegistrationReceiver();
    private final BroadcastReceiver mGcmMessageReceiver = new GcmMessageReceiver();

    @Override
    protected void onStart() {
        super.onStart();

        mBroadcastManager.registerReceiver(mGcmRegistrationReceiver,
                new IntentFilter(GcmRegistrationService.ACTION_GCM_REGISTRATION));
        mBroadcastManager.registerReceiver(mGcmMessageReceiver,
                new IntentFilter(GcmMessageService.ACTION_GCM_MESSAGE_RECEIVED));

        if (TextUtils.isEmpty(mAccountManager.getGcmToken(mAccount))) {
            startService(new Intent(getApplicationContext(), GcmRegistrationService.class)
                    .putExtra(TaxiAccountManager.EXTRA_ACCOUNT, mAccount));
        }

        startService(new Intent(getApplicationContext(), NtpService.class));
        startService(new Intent(getApplicationContext(), OrderMonitoringService.class)
                .putExtra(TaxiAccountManager.EXTRA_ACCOUNT, mAccount));
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
