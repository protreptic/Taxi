package name.peterbukhal.android.taxi.client.service.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikService;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikServiceImpl;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String LOG_TAG = "SyncAdapter";

    protected final JsonTaxikService mTaxiService;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mTaxiService = JsonTaxikServiceImpl.instance().service();    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);

        mTaxiService = JsonTaxikServiceImpl.instance().service();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {
        Log.d(LOG_TAG, "Synchronization started.");

        //noinspection TryWithIdenticalCatches
        try {
            List<City> cities = mTaxiService.queryCities().execute().body().getCities();

            for (City city : cities) {
                Log.d(LOG_TAG, city.getName());
            }

            TimeUnit.SECONDS.sleep(15);

            Log.d(LOG_TAG, "Synchronization successfully finished.");
        } catch (Exception e) {
            Log.e(LOG_TAG, "Synchronization failed.");
        }
    }

}