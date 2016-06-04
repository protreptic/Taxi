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

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikService;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikServiceImpl;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryCitiesRequest;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public final class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String LOG_TAG = "SyncAdapter";

    private final JsonTaxikService mTaxiService;
    private final TaxiAccountManager mAccountManager;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mTaxiService = JsonTaxikServiceImpl.instance().service();
        mAccountManager = TaxiAccountManager.get(context);
    }

    @SuppressWarnings("unused")
    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);

        mTaxiService = JsonTaxikServiceImpl.instance().service();
        mAccountManager = TaxiAccountManager.get(context);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {
        Log.d(LOG_TAG, "Synchronization started.");

        String token = mAccountManager.peekAuthToken(account);

        //noinspection TryWithIdenticalCatches
        try {
            List<City> cities = mTaxiService.queryCities(new QueryCitiesRequest(token)).execute().body().getCities();

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