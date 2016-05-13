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

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;

import static name.peterbukhal.android.taxi.client.account.TaxiAccountManager.EXTRA_ACCOUNT;
import static name.peterbukhal.android.taxi.client.account.TaxiAccountManager.EXTRA_ACCOUNT_TOKEN;
import static name.peterbukhal.android.taxi.client.account.TaxiAccountManager.PickUpAccountAdapter.ADD_ACCOUNT_ITEM_ID;

/**
 * Created by
 * petronic on 23.04.16.
 */
public class LauncherActivity extends AppCompatActivity {

    private TaxiAccountManager mAccountManager;

    private void runApplication(Account account, String token) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra(EXTRA_ACCOUNT, account);
        intent.putExtra(EXTRA_ACCOUNT_TOKEN, token);

        startActivity(intent);
    }

    private void signIn(Account account) {
        String accountToken = mAccountManager.peekAuthToken(account);

        mAccountManager.invalidateAuthToken(accountToken);
        mAccountManager.getAuthToken(account, new AccountManagerCallback<Bundle>() {

            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Bundle bundle = future.getResult();

                    Intent keyIntent = bundle.getParcelable(AccountManager.KEY_INTENT);

                    if (keyIntent != null) {
                        signUp();
                    } else {
                        String accountName = bundle.getString(AccountManager.KEY_ACCOUNT_NAME);
                        String accountType = bundle.getString(AccountManager.KEY_ACCOUNT_TYPE);
                        String accountToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);

                        runApplication(new Account(accountName, accountType), accountToken);
                    }
                } catch (OperationCanceledException e) {
                    pickUpAccountDialog();
                } catch (AuthenticatorException e) {
                    pickUpAccountDialog();
                } catch (IOException e) {
                    pickUpAccountDialog();
                }
            }
        });
    }

    private void signUp() {
        mAccountManager.addAccount(this, new AccountManagerCallback<Bundle>() {

            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Bundle bundle = future.getResult();

                    String accountName = bundle.getString(AccountManager.KEY_ACCOUNT_NAME);
                    String accountType = bundle.getString(AccountManager.KEY_ACCOUNT_TYPE);

                    signIn(new Account(accountName, accountType));
                } catch (OperationCanceledException e) {
                    pickUpAccountDialog();
                } catch (AuthenticatorException e) {
                    pickUpAccountDialog();
                } catch (IOException e) {
                    pickUpAccountDialog();
                }
            }

        });
    }

    private void pickUpAccountDialog() {
        final TaxiAccountManager.PickUpAccountAdapter accountAdapter = mAccountManager.getAccountAdapter();

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

    @Override
    protected void onStart() {
        super.onStart();

        pickUpAccountDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAccountManager = TaxiAccountManager.get(getApplicationContext());
    }

}
