package name.peterbukhal.android.taxi.client.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.account.TaxiClientAccount;

import static name.peterbukhal.android.taxi.client.account.TaxiAccountManager.EXTRA_ACCOUNT;
import static name.peterbukhal.android.taxi.client.account.TaxiAccountManager.PickUpAccountAdapter.ADD_ACCOUNT_ITEM_ID;

/**
 * Created by
 *      petronic on 23.04.16.
 */
public final class LauncherActivity extends AppCompatActivity {

    private TaxiAccountManager mAccountManager;

    private void runApplication(Account account) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra(EXTRA_ACCOUNT, account);
        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

    private void pickUpAccountDialog() {
        final TaxiAccountManager.PickUpAccountAdapter accountAdapter
                = mAccountManager.getAccountAdapter();

        new AlertDialog.Builder(this)
                .setSingleChoiceItems(accountAdapter, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        if (accountAdapter.getItemId(which) == ADD_ACCOUNT_ITEM_ID) {
                            signUp();
                        } else {
                            signIn(accountAdapter.getItem(which));
                        }
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                })
                .show();
    }

    private void signIn(Account account) {
        mAccountManager.getToken(account, new AccountManagerCallback<Bundle>() {

            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                //noinspection TryWithIdenticalCatches,EmptyCatchBlock
                try {
                    Bundle bundle = future.getResult();

                    Intent keyIntent = bundle.getParcelable(AccountManager.KEY_INTENT);

                    if (keyIntent != null) {
                        signUp();
                    } else {
                        if (bundle.containsKey(AccountManager.KEY_ACCOUNT_NAME)
                                && bundle.containsKey(AccountManager.KEY_ACCOUNT_TYPE)) {

                            runApplication(new TaxiClientAccount(
                                    bundle.getString(AccountManager.KEY_ACCOUNT_NAME)));
                        }
                    }
                } catch (OperationCanceledException e) {

                } catch (AuthenticatorException e) {

                } catch (IOException e) {

                }
            }
        });
    }

    private void signUp() {
        mAccountManager.addAccount(this, new AccountManagerCallback<Bundle>() {

            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                //noinspection TryWithIdenticalCatches,EmptyCatchBlock
                try {
                    Bundle bundle = future.getResult();

                    signIn(new TaxiClientAccount(
                            bundle.getString(AccountManager.KEY_ACCOUNT_NAME)));
                } catch (OperationCanceledException e) {

                } catch (AuthenticatorException e) {

                } catch (IOException e) {

                }
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Account defaultAccount = mAccountManager.getDefaultAccount();

        if (defaultAccount.name.equals("#")) {
            pickUpAccountDialog();
        } else {
            signIn(defaultAccount);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_launcher);

        mAccountManager = TaxiAccountManager.get(getApplicationContext());
    }

}
