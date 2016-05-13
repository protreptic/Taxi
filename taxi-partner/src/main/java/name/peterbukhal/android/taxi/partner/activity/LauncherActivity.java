package name.peterbukhal.android.taxi.partner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by
 * petronic on 13.05.16.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }
}
