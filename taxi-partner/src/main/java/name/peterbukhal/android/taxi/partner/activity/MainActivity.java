package name.peterbukhal.android.taxi.partner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import name.peterbukhal.android.taxi.partner.R;
import name.peterbukhal.android.taxi.partner.service.TaximeterService;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_main);

        startService(new Intent(getApplicationContext(), TaximeterService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopService(new Intent(getApplicationContext(), TaximeterService.class));
    }
}
