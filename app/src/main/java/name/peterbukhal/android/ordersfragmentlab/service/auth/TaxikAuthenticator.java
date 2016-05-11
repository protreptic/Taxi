package name.peterbukhal.android.ordersfragmentlab.service.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

import name.peterbukhal.android.ordersfragmentlab.activity.RegistrationActivity;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.JsonTaxikService;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.JsonTaxikServiceImpl;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.response.SubmitSmsCodeResponse;
import retrofit2.Response;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class TaxikAuthenticator extends AbstractAccountAuthenticator {

    private Context mContext;

    public TaxikAuthenticator(Context context) {
        super(context);

        mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String s, String s2,
                             String[] strings, Bundle options) throws NetworkErrorException {
        Intent intent = new Intent(mContext, RegistrationActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account,
                                     Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, final Account account,
                               String s, Bundle options) throws NetworkErrorException {
        String token =
                mContext.getSharedPreferences("main", Context.MODE_PRIVATE).getString("token", "#");

        if (!token.equals("#")) {
            Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, token);

            return result;
        }

        JsonTaxikService taxikService = JsonTaxikServiceImpl.instance().service();

        try {
            Response<SubmitPhoneNumberResponse> response1 =
                    taxikService.querySubmitPhoneNumber(
                            new SubmitPhoneNumberRequest(account.name, "")).execute();

            Response<SubmitSmsCodeResponse> response2 =
                    taxikService.querySubmitSmsCode(
                            new SubmitSmsCodeRequest("", 1, Integer.valueOf(response1.body().getSmsCode()), "+79167749891", null)).execute();

            Toast.makeText(mContext, "New token obtained", Toast.LENGTH_LONG).show();

            Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, response2.body().getToken());

            mContext.getSharedPreferences("main", Context.MODE_PRIVATE)
                    .edit()
                    .putString("token", response2.body().getToken())
                    .apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getAuthTokenLabel(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account,
                                    String s, Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account,
                              String[] strings) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

}
