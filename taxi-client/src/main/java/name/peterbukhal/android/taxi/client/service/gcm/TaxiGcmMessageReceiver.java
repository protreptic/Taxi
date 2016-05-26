package name.peterbukhal.android.taxi.client.service.gcm;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;

import static name.peterbukhal.android.taxi.client.service.gcm.TaxiGcmListenerService.EXTRA_GCM_MESSAGE;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public final class TaxiGcmMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(TaxiGcmListenerService.ACTION_GCM_MESSAGE_RECEIVED)) {
            if (intent.getExtras() != null && intent.getExtras().containsKey(EXTRA_GCM_MESSAGE)) {
                TaxiGcmMessage message = intent.getExtras().getParcelable(EXTRA_GCM_MESSAGE);

                if (message.getEventId() == 666) {
                    TaxiAccountManager accountManager = TaxiAccountManager.get(context);

                    Account defaultAccount = accountManager.getDefaultAccount();
                    String token = accountManager.peekAuthToken(defaultAccount);

                    accountManager.invalidateToken(token);
                    accountManager.getToken(defaultAccount, new AccountManagerCallback<Bundle>() {

                        @Override
                        public void run(AccountManagerFuture<Bundle> future) {

                        }
                    });

                    Toast.makeText(context, "Token for client " + defaultAccount.name
                            + " has been invalidated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "New message received: " +
                            message, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
