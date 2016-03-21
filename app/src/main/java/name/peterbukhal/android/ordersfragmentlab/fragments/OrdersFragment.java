package name.peterbukhal.android.ordersfragmentlab.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

import name.peterbukhal.android.ordersfragmentlab.R;
import name.peterbukhal.android.ordersfragmentlab.model.Order;
import name.peterbukhal.android.ordersfragmentlab.model.Orders;
import name.peterbukhal.android.ordersfragmentlab.model.api.json.TaxikGson;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.QueryOrdersRequest;
import name.peterbukhal.android.ordersfragmentlab.service.TaxikService;
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

    public static Fragment newInstance() {
        Bundle arguments = new Bundle();

        Fragment fragment = new OrdersFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private ListView mOrdersView;
    private Orders mOrders = new Orders(Collections.<Order>emptyList(), 0L);
    private OrdersAdapter mOrdersAdapter;

    private class OrdersAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mOrders.getOrders().size();
        }

        @Override
        public Order getItem(int position) {
            return mOrders.getOrders().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            OrderViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_2, parent, false);

                holder = new OrderViewHolder();
                holder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
                holder.text2 = (TextView) convertView.findViewById(android.R.id.text2);

                convertView.setTag(holder);
            } else {
                holder = (OrderViewHolder) convertView.getTag();
            }

            Order order = getItem(position);

            holder.text1.setText(String.valueOf(order));
            holder.text2.setText("");

            return convertView;
        }

        private class OrderViewHolder {
            TextView text1;
            TextView text2;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mOrdersView = (ListView) inflater.inflate(R.layout.f_orders, container, false);

        return mOrdersView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TaxikService taxikService = TaxikGson.service();

        mOrdersAdapter = new OrdersAdapter();
        mOrdersView.setAdapter(mOrdersAdapter);

        String token = getActivity().getSharedPreferences("main", Context.MODE_PRIVATE).getString("token", "");

        Call<Orders> request = taxikService.queryOrders(new QueryOrdersRequest(token, 0, 500, QueryOrdersRequest.RequestOrderType.ALL));
        request.enqueue(new Callback<Orders>() {

            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                mOrders = response.body();
                mOrdersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                Toast.makeText(getActivity(), "QueryOrders error!", Toast.LENGTH_LONG).show();
            }

        });
    }
}
