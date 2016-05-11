package name.peterbukhal.android.ordersfragmentlab.account;

import android.accounts.Account;
import android.annotation.SuppressLint;

/**
 * Created by
 * petronic on 11.05.16.
 */
@SuppressLint("ParcelCreator")
public class TaxikAccount extends Account {

    public static final String ACCOUNT_AUTHORITY = "name.peterbukhal.android.ordersfragmentlab";
    public static final String ACCOUNT_TYPE = "name.peterbukhal.android.ordersfragmentlab";

    public TaxikAccount(String name) {
        super(name, ACCOUNT_TYPE);
    }

}
