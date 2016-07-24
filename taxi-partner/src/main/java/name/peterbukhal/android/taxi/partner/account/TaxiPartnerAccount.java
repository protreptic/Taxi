package name.peterbukhal.android.taxi.partner.account;

import android.accounts.Account;
import android.annotation.SuppressLint;

/**
 * Created by
 *      petronic on 11.05.16.
 */
@SuppressLint("ParcelCreator")
public final class TaxiPartnerAccount extends Account {

    public static final String ACCOUNT_AUTHORITY = "name.peterbukhal.android.taxi.client";
    public static final String ACCOUNT_TYPE = "name.peterbukhal.android.taxi.client";

    public TaxiPartnerAccount(String name) {
        super(name, ACCOUNT_TYPE);
    }

}
