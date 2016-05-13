package name.peterbukhal.android.taxi.client.unused;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import name.peterbukhal.android.taxi.client.model.City;

/**
 * Created by
 * petronic on 03.04.16.
 */
public class CitiesAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<City> mCities;

    public CitiesAdapter(Context context, List<City> cities) {
        mLayoutInflater = LayoutInflater.from(context);
        mCities.addAll(cities);
    }

    @Override
    public int getCount() {
        return mCities.size();
    }

    @Override
    public City getItem(int position) {
        return mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityViewHolder holder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false);

            holder = new CityViewHolder();
            holder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(android.R.id.text2);

            convertView.setTag(holder);
        } else {
            holder = (CityViewHolder) convertView.getTag();
        }

        City city = getItem(position);

        holder.text1.setText(String.valueOf(city.getId()));
        holder.text2.setText(city.getName());

        return convertView;
    }

    private class CityViewHolder {
        TextView text1;
        TextView text2;
    }
}
