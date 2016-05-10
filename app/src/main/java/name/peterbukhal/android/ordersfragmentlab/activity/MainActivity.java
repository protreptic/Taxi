package name.peterbukhal.android.ordersfragmentlab.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import name.peterbukhal.android.ordersfragmentlab.R;
import name.peterbukhal.android.ordersfragmentlab.fragment.OrdersFragment;
import name.peterbukhal.android.ordersfragmentlab.fragment.UserOrdersFragment;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.JsonTaxikService;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.JsonTaxikServiceImpl;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.request.QueryOrdersRequest.OrderType;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.response.SubmitSmsCodeResponse;
import name.peterbukhal.android.ordersfragmentlab.service.OrderStateMonitoringService;
import name.peterbukhal.android.ordersfragmentlab.service.gcm.TaxikGcmListenerService;
import name.peterbukhal.android.ordersfragmentlab.service.gcm.TaxikGcmRegistrationIntentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager broadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());

        final JsonTaxikService taxikService = JsonTaxikServiceImpl.instance().service();

        String token = getSharedPreferences("main", Context.MODE_PRIVATE).getString("token", "");

        if (token.isEmpty()) {
            SubmitPhoneNumberRequest request = new SubmitPhoneNumberRequest("+79167749891", "");

            Call<SubmitPhoneNumberResponse> callQuerySubmitPhoneNumber = taxikService.querySubmitPhoneNumber(request);
            callQuerySubmitPhoneNumber.enqueue(new Callback<SubmitPhoneNumberResponse>() {

                @Override
                public void onResponse(Call<SubmitPhoneNumberResponse> call, Response<SubmitPhoneNumberResponse> response) {
                    SubmitPhoneNumberResponse s = response.body();

                    Call<SubmitSmsCodeResponse> callSubmitSmsCode = taxikService.querySubmitSmsCode(new SubmitSmsCodeRequest("", 1, Integer.valueOf(s.getSmsCode()), "+79167749891", null));
                    callSubmitSmsCode.enqueue(new Callback<SubmitSmsCodeResponse>() {

                        @Override
                        public void onResponse(Call<SubmitSmsCodeResponse> call, Response<SubmitSmsCodeResponse> response) {
                            getSharedPreferences("main", Context.MODE_PRIVATE)
                                    .edit()
                                    .putString("token", response.body().getToken())
                                    .apply();

                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragmentContent, OrdersFragment.newInstance(OrderType.ALL))
                                    .commit();

                            Toast.makeText(getApplicationContext(), "New token obtained", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<SubmitSmsCodeResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Call querySubmitSmsCode error", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<SubmitPhoneNumberResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Call QuerySubmitPhoneNumber error", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContent, UserOrdersFragment.newInstance(), OrdersFragment.FRAGMENT_TAG_ORDERS)
                    .commit();
        }
    }

    private BroadcastReceiver gcmRegistrationReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(TaxikGcmRegistrationIntentService.ACTION_GCM_REGISTRATION)) {
                getSharedPreferences(getPackageName(), MODE_PRIVATE)
                        .edit()
                        .putString("gcm_token", intent.getStringExtra(TaxikGcmRegistrationIntentService.EXTRA_GCM_TOKEN))
                        .commit();
            }
        }

    };

    private BroadcastReceiver gcmNewMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(TaxikGcmListenerService.ACTION_GCM_NEW_MESSAGE)) {

            }
        }

    };

    @Override
    protected void onStart() {
        super.onStart();

        startService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));

        broadcastManager.registerReceiver(gcmRegistrationReceiver,
                new IntentFilter(TaxikGcmRegistrationIntentService.ACTION_GCM_REGISTRATION));
        broadcastManager.registerReceiver(gcmNewMessageReceiver,
                new IntentFilter(TaxikGcmListenerService.ACTION_GCM_NEW_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopService(new Intent(getApplicationContext(), OrderStateMonitoringService.class));

        broadcastManager.unregisterReceiver(gcmRegistrationReceiver);
        broadcastManager.unregisterReceiver(gcmNewMessageReceiver);
    }
}
