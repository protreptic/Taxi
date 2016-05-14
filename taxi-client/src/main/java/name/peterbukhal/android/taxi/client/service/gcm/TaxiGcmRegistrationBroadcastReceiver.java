package name.peterbukhal.android.taxi.client.service.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;

import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikService;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikServiceImpl;
import name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.RegisterDeviceResponse;
import retrofit2.Response;

import static name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequest.DevicePlatform.ANDROID;

/**
 * Created by
 *      petronic on 14.05.16.
 */
public class TaxiGcmRegistrationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(TaxiGcmRegistrationService.ACTION_GCM_REGISTRATION)) {
            String token =
                    context.getSharedPreferences("main", Context.MODE_PRIVATE)
                            .getString("token", "#");

            String gcmToken =
                    intent.getStringExtra(TaxiGcmRegistrationService.EXTRA_GCM_TOKEN);

            context.getSharedPreferences("main", Context.MODE_PRIVATE)
                    .edit()
                    .putString("gcm_token", gcmToken)
                    .commit();

            JsonTaxikService taxikService = JsonTaxikServiceImpl.instance().service();

            try {
                Response<RegisterDeviceResponse> response = taxikService.queryRegisterDevice(
                        new RegisterDeviceRequest(token, gcmToken, ANDROID))
                        .execute();

                if (response.body().getStatus()) {
                    Toast.makeText(context,
                            "Device registered", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
