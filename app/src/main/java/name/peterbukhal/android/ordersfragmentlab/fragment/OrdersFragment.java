package name.peterbukhal.android.ordersfragmentlab.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

import name.peterbukhal.android.ordersfragmentlab.R;
import name.peterbukhal.android.ordersfragmentlab.model.Order;
import name.peterbukhal.android.ordersfragmentlab.model.Orders;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.JsonTaxikServiceImpl;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.request.QueryOrdersRequest;
import name.peterbukhal.android.ordersfragmentlab.server.api.json.request.QueryOrdersRequest.OrderType;
import name.peterbukhal.android.ordersfragmentlab.model.impl.OrdersImpl;
import name.peterbukhal.android.ordersfragmentlab.service.OrderStateMonitoringService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 16/03/16 13:24 by
 *
 * @author Peter Bukhal (petr@taxik.ru)
 */
public class OrdersFragment extends Fragment {

    public static final String FRAGMENT_TAG_ORDERS = "fragment_tag_orders";
    public static final String ARG_ORDER_TYPE = "arg_order_type";

    public static Fragment newInstance(OrderType orderType) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_ORDER_TYPE, orderType);

        Fragment fragment = new OrdersFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private OrderType mOrderType;
    private String mToken;

    private Orders mOrders = new OrdersImpl(Collections.<Order>emptyList());
    private OrdersAdapter mOrdersAdapter = new OrdersAdapter();

    private class OrdersAdapter extends RecyclerView.Adapter<OrderViewHolder> {

        @Override
        public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new OrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false));
        }

        @Override
        public void onBindViewHolder(OrderViewHolder holder, int position) {
            final Order order = mOrders.getOrders().get(position);

            holder.text1.setText(String.valueOf(order));
            holder.text2.setText(String.valueOf(order.getId()) + " (" + order.getOrderProgress() + ")");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContent, OrderFragment.newInstance(order))
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mOrders.getTotalCount();
        }

    }

    private class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;

        public OrderViewHolder(View itemView) {
            super(itemView);

            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            text2 = (TextView) itemView.findViewById(android.R.id.text2);
        }

    }

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) inflater.inflate(R.layout.f_orders, container, false);

        if (contentView != null) {
            mRecyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }

            });

            mProgressBar = (ProgressBar) contentView.findViewById(R.id.progressBar);
        }

        return contentView;
    }

    private static final String EXTRA_TOKEN = "extra_token";
    private static final String EXTRA_ORDERS = "extra_orders";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(ARG_ORDER_TYPE, mOrderType);
        outState.putString(EXTRA_TOKEN, mToken);
        outState.putParcelable(EXTRA_ORDERS, mOrders);
    }

    private LocalBroadcastManager broadcastManager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        broadcastManager = LocalBroadcastManager.getInstance(getActivity());

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_ORDER_TYPE)) {
            mOrderType = (OrderType) savedInstanceState.getSerializable(ARG_ORDER_TYPE);
            mToken = savedInstanceState.getString(EXTRA_TOKEN);
            mOrders = savedInstanceState.getParcelable(EXTRA_ORDERS);
        } else if (getArguments() != null && getArguments().containsKey(ARG_ORDER_TYPE)) {
            mOrderType = (OrderType) getArguments().getSerializable(ARG_ORDER_TYPE);
            mToken = getActivity().getSharedPreferences("main", Context.MODE_PRIVATE).getString("token", "");

            updateOrders();
        }

        mRecyclerView.setAdapter(mOrdersAdapter);
    }

    private BroadcastReceiver orderStateChangedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isAdded()) return;

            updateOrders();
        }

    };

    @Override
    public void onStart() {
        super.onStart();

        broadcastManager.registerReceiver(orderStateChangedReceiver, new IntentFilter(OrderStateMonitoringService.ACTION_ORDER_STATE_CHANGED));
    }

    @Override
    public void onStop() {
        super.onStop();

        broadcastManager.unregisterReceiver(orderStateChangedReceiver);
    }

    private void updateOrders() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        Call<Orders> request = JsonTaxikServiceImpl.instance().service()
                .queryOrders(new QueryOrdersRequest(mToken, 0, 5, mOrderType));
        request.enqueue(new Callback<Orders>() {

            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                mOrders = response.body();
                mOrdersAdapter.notifyDataSetChanged();

                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                Toast.makeText(getActivity(), "QueryOrders error!", Toast.LENGTH_LONG).show();

                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }

        });
    }

}
