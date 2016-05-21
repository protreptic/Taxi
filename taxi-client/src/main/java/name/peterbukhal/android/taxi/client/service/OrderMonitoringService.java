package name.peterbukhal.android.taxi.client.service;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import name.peterbukhal.android.taxi.client.account.TaxiAccountManager;
import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.Orders;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikService;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikServiceImpl;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrdersRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by
 *      petronic on 09.05.16.
 */
public final class OrderMonitoringService extends Service {

    private static final String LOG_TAG = "OrderMonitoring";

    private TaxiAccountManager mAccountManager;
    private String mToken;
    private JsonTaxikService mTaxikService;
    private ScheduledExecutorService mExecutorService;
    private LocalBroadcastManager mBroadcastManager;

    private boolean mIsStopped;

    @Override
    public void onCreate() {
        super.onCreate();

        mAccountManager = TaxiAccountManager.get(getApplicationContext());
        mTaxikService = JsonTaxikServiceImpl.instance().service();
        mExecutorService = Executors.newSingleThreadScheduledExecutor();
        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    private final Map<Order, PriorityQueue<Order.ProgressState>> monitoringQueue = new HashMap<>();

    private void updateOrders() {
        if (mIsStopped) return;

        final Call<Orders> request =
                mTaxikService.queryOrders(
                        new QueryOrdersRequest(mToken, 0, 5, QueryOrdersRequest.OrderType.ACTIVE));

        request.enqueue(new Callback<Orders>() {

            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                List<Order> activeOrders = response.body().getOrders();

                if (activeOrders.isEmpty()) {
                    Log.d(LOG_TAG, "No active orders.");

                    mIsStopped = true;
                    stopSelf();
                } else {
                    Log.d(LOG_TAG, activeOrders.size() + " active orders found.");

                    for (Order order : activeOrders) {
                        synchronized (monitoringQueue) {
                                if (monitoringQueue.containsKey(order)) {
                                    Order.ProgressState currentProgressState =
                                            monitoringQueue.get(order).peek();

                                    monitoringQueue.get(order).offer(order.getOrderProgress());

                                    Order.ProgressState updatedProgressState =
                                            monitoringQueue.get(order).peek();

                                    if (currentProgressState.getValue() != updatedProgressState.getValue()) {
                                        Log.d(LOG_TAG, "Order with id " + order.getId() +
                                                " updated, order status is " + updatedProgressState + ".");

                                        broadcastOrderState(order);
                                    } else {
                                        Log.d(LOG_TAG, "Order with id " + order.getId() +
                                                " unchanged, order status is " + currentProgressState + ".");
                                    }
                                } else {
                                    monitoringQueue.put(order, new PriorityQueue<>(10,
                                            new Comparator<Order.ProgressState>() {

                                                @Override
                                                public int compare(Order.ProgressState lhs, Order.ProgressState rhs) {
                                                    if (lhs.getValue() < rhs.getValue()) return 1;
                                                    if (lhs.getValue() == rhs.getValue()) return 0;
                                                    if (lhs.getValue() > rhs.getValue()) return -1;

                                                    throw new RuntimeException();
                                                }

                                            }));
                                    monitoringQueue.get(order).offer(order.getOrderProgress());

                                    broadcastOrderState(order);
                                }
                        }
                    }

                    if (!mIsStopped) {
                        mExecutorService.schedule(new Runnable() {

                            @Override
                            public void run() {
                                updateOrders();
                            }

                        }, 30, TimeUnit.SECONDS);
                    }
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                mIsStopped = true;
                stopSelf();
            }

        });
    }

    public static final String ACTION_ORDER_STATE_CHANGED =
            "name.peterbukhal.android.taxi.client.service.action.ACTION_ORDER_STATUS_CHANGED";

    public static final String EXTRA_ORDER = "extra_order";

    private void broadcastOrderState(Order order) {
        Intent intent = new Intent(ACTION_ORDER_STATE_CHANGED);
        intent.putExtra(EXTRA_ORDER, order);

        mBroadcastManager.sendBroadcast(intent);

        Log.d(LOG_TAG, "Broadcast state for order " + order.getId() +
                " (" + order.getOrderProgress() + ").");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras() != null
                && intent.getExtras().containsKey(TaxiAccountManager.EXTRA_ACCOUNT)) {
            mToken = mAccountManager.peekAuthToken(
                    (Account) intent.getParcelableExtra(TaxiAccountManager.EXTRA_ACCOUNT));

            updateOrders();

            Log.d(LOG_TAG, "Service started.");
        } else {
            Log.d(LOG_TAG, "Account not found.");
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mExecutorService.shutdown();
        mIsStopped = true;
        Log.d(LOG_TAG, "Service stopped.");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
