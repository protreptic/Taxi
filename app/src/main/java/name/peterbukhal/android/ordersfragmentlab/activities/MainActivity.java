package name.peterbukhal.android.ordersfragmentlab.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import name.peterbukhal.android.ordersfragmentlab.R;
import name.peterbukhal.android.ordersfragmentlab.fragments.OrdersFragment;
import name.peterbukhal.android.ordersfragmentlab.fragments.UserOrdersFragment;
import name.peterbukhal.android.ordersfragmentlab.model.api.json.TaxikGson;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.QueryOrdersRequest.OrderType;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitSmsCodeResponse;
import name.peterbukhal.android.ordersfragmentlab.service.TaxikService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TaxikService taxikService = TaxikGson.instance().service();

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

}
