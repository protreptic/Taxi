package name.peterbukhal.android.taxi.client.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.account.TaxiClientAccount;

/**
 * Created by
 *      petronic on 23.04.16.
 */
public final class RegistrationActivity extends AppCompatActivity {

    private static final String LOG_TAG = "RegistrationActivity";

    private TaxiAccountManager mAccountManager;
    private RegistrationTask mAuthTask;

    private TextView mMessageTextView;
    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    private String mUserName;
    private String mPassword;

    private AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
    private Bundle mResultBundle = null;

    public final void setAccountAuthenticatorResult(Bundle result) {
        mResultBundle = result;
    }

    public void finish() {
        if (mAccountAuthenticatorResponse != null) {
            if (mResultBundle != null) {
                mAccountAuthenticatorResponse.onResult(mResultBundle);
            } else {
                mAccountAuthenticatorResponse.onError(
                        AccountManager.ERROR_CODE_CANCELED, "canceled");
            }
            mAccountAuthenticatorResponse = null;
        }

        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAccountAuthenticatorResponse =
                getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

        if (mAccountAuthenticatorResponse != null) {
            mAccountAuthenticatorResponse.onRequestContinued();
        }

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
            mAccountManager.setToken(account, authToken);
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
        if (!TextUtils.isEmpty(authToken)) {
            finishLogin(authToken);
        } else {
            mMessageTextView.setText(R.string.error_message);

            mLoginEditText.requestFocus();
            mPasswordEditText.setText("");
        }

        mAuthTask = null;
    }

    public void onAuthenticationCancel() {
        mAuthTask = null;
    }

    public class RegistrationTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                return "#";
            } catch (Exception e) {
                Log.e(LOG_TAG, "RegistrationTask: failed to authenticate");
                Log.i(LOG_TAG, e.toString());

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
