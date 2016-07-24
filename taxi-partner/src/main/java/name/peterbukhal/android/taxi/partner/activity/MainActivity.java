package name.peterbukhal.android.taxi.partner.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import name.peterbukhal.android.taxi.partner.fragment.MapFragment;
import name.peterbukhal.android.taxi.partner.R;
import name.peterbukhal.android.taxi.partner.service.NetworkService;
import name.peterbukhal.android.taxi.partner.service.TaximeterService;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content,
                            MapFragment.newInstance(), MapFragment.FRAGMENT_TAG_MAP)
                    .commit();
        }

        startService(new Intent(getApplicationContext(), TaximeterService.class));
        startService(new Intent(getApplicationContext(), NetworkService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopService(new Intent(getApplicationContext(), TaximeterService.class));
        stopService(new Intent(getApplicationContext(), NetworkService.class));
    }
}
