package name.peterbukhal.android.taxi.partner.service.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import name.peterbukhal.android.taxi.partner.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.partner.activity.RegistrationActivity;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class TaxiAuthenticator extends AbstractAccountAuthenticator {

    public static final String LOG_TAG = "Authenticator";

    private final Context mContext;
    private final TaxiAccountManager mAccountManager;

    public TaxiAuthenticator(Context context) {
        super(context);

        mContext = context;
        mAccountManager = TaxiAccountManager.get(context);
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType,
                             String authTokenType, String[] requiredFeatures, Bundle options)
            throws NetworkErrorException {
        Intent intent = new Intent(mContext, RegistrationActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account,
                                     Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, final Account account,
                               String authTokenType, Bundle options) throws NetworkErrorException {
        String token = mAccountManager.peekAuthToken(account);

        if (!token.equals("#")) {
            Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, token);

            return result;
        }

//        JsonTaxikService taxikService = JsonTaxikServiceImpl.instance().service();
//
//        try {
//            Response<SubmitPhoneNumberResponse> response1 =
//                    taxikService.submitPhoneNumber(
//                            new SubmitPhoneNumberRequest(account.name, "")).execute();
//
//            Response<SubmitSmsCodeResponse> response2 =
//                    taxikService.submitSmsCode(
//                            new SubmitSmsCodeRequest("", "", 1, Integer.valueOf(response1.body().getSmsCode()), account.name, null)).execute();
//
//            Log.d(LOG_TAG, "New token obtained for user " + account.name);
//
//            mAccountManager.setToken(account, response2.body().getToken());
//
//            Bundle result = new Bundle();
//            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
//            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
//            result.putString(AccountManager.KEY_AUTHTOKEN, response2.body().getToken());
//
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return Bundle.EMPTY;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account,
                                    String authTokenType, Bundle options) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account,
                              String[] features) throws NetworkErrorException {
        throw new UnsupportedOperationException();
    }

}
