package name.peterbukhal.android.taxi.partner.activity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class LauncherActivity extends AppCompatActivity {

    private void runApplication(Account account) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("extra_account", account);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runApplication(null);
    }

}
