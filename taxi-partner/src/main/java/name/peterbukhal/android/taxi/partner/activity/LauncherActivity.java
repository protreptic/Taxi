package name.peterbukhal.android.taxi.partner.activity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by
 *      petronic on 13.05.16.
 */
public final class LauncherActivity extends AppCompatActivity {

    private void runApplication(Account account) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("extra_account", account);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runApplication(null);
    }

}
