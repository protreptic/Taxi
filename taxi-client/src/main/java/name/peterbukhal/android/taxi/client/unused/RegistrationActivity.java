package name.peterbukhal.android.taxi.client.unused;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.account.TaxiClientAccount;
import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;

/**
 * Created by
 * petronic on 23.04.16.
 */
public class RegistrationActivity extends AccountAuthenticatorActivity {

    private static final String TAG = "AuthenticatorActivity";

    private TaxiAccountManager mAccountManager;

    private RegistrationTask mAuthTask;

    private TextView mMessageTextView;
    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    private String mUserName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_registration);

        mAccountManager = TaxiAccountManager.get(getApplicationContext());

        mMessageTextView = (TextView) findViewById(R.id.message);
        mLoginEditText = (EditText) findViewById(R.id.login);
        mPasswordEditText = (EditText) findViewById(R.id.password);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mUserName = mLoginEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();

                mAuthTask = new RegistrationTask();
                mAuthTask.execute();
            }

        });
    }

    private void finishLogin(String authToken) {
        Account account = new Account(mUserName, TaxiClientAccount.ACCOUNT_TYPE);

        if (mAccountManager.isExists(account)) {
            mAccountManager.setPassword(account, mPassword);
        } else {
            mAccountManager.addAccountExplicitly(account, mPassword);
        }

        Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, mUserName);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, TaxiClientAccount.ACCOUNT_TYPE);

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Called when the authentication process completes (see attemptLogin()).
     *
     * @param authToken the authentication token returned by the server, or NULL if
     *            authentication failed.
     */
    public void onAuthenticationResult(String authToken) {
        boolean success = !TextUtils.isEmpty(authToken);
        Log.i(TAG, "onAuthenticationResult(" + success + ")");

        if (success) {
            finishLogin(authToken);
        } else {
            Log.e(TAG, "onAuthenticationResult: failed to authenticate");

            mMessageTextView.setText(R.string.error_message);

            mLoginEditText.requestFocus();
            mPasswordEditText.setText("");
        }

        // Our task is complete, so clear it out
        mAuthTask = null;
    }

    public void onAuthenticationCancel() {
        Log.i(TAG, "onAuthenticationCancel()");

        // Our task is complete, so clear it out
        mAuthTask = null;
    }

    public class RegistrationTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                return "#";
            } catch (Exception e) {
                Log.e(TAG, "RegistrationTask: failed to authenticate");
                Log.i(TAG, e.toString());

                return null;
            }
        }

        @Override
        protected void onPostExecute(String authToken) {
            // On a successful authentication, call back into the Activity to
            // communicate the authToken (or null for an error).
            onAuthenticationResult(authToken);
        }

        @Override
        protected void onCancelled() {
            // If the action was canceled (by the user clicking the cancel
            // button in the progress dialog), then call back into the
            // activity to let it know.
            onAuthenticationCancel();
        }

    }

}
