package name.peterbukhal.android.ordersfragmentlab.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.peterbukhal.android.ordersfragmentlab.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    public interface TaxikService {

        @POST("query_cities")
        Call<Cities> queryCities();

        @POST("submit_phone_number")
        Call<SubmitPhoneNumberResponse> querySubmitPhoneNumber(@Body SubmitPhoneNumberRequest request);

    }

    public static class SubmitPhoneNumberRequest {

        private String phoneNumber;
        private String promoCode;

        public SubmitPhoneNumberRequest(String phoneNumber, String promoCode) {
            this.phoneNumber = phoneNumber;
            this.promoCode = promoCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getPromoCode() {
            return promoCode;
        }

    }

    public static class SubmitPhoneNumberResponse {

        private String name;
        private Integer source;
        private Integer smsCode;
        private String phoneNumber;
        private String promoCode;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getSource() {
            return source;
        }

        public void setSource(Integer source) {
            this.source = source;
        }

        public Integer getSmsCode() {
            return smsCode;
        }

        public void setSmsCode(Integer smsCode) {
            this.smsCode = smsCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPromoCode() {
            return promoCode;
        }

        public void setPromoCode(String promoCode) {
            this.promoCode = promoCode;
        }

    }

    public static class SubmitPhoneNumberResponseJsonDeserializer implements JsonDeserializer<SubmitPhoneNumberResponse> {

        @Override
        public SubmitPhoneNumberResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            SubmitPhoneNumberResponse submitPhoneNumberResponse = new SubmitPhoneNumberResponse();
            submitPhoneNumberResponse.setName(json.getAsJsonObject().get("name").getAsString());
            submitPhoneNumberResponse.setPhoneNumber(json.getAsJsonObject().get("phone_number").getAsString());
            submitPhoneNumberResponse.setSource(json.getAsJsonObject().get("source").getAsInt());
            submitPhoneNumberResponse.setPromoCode(json.getAsJsonObject().get("promo_code").getAsString());
            submitPhoneNumberResponse.setSmsCode(json.getAsJsonObject().get("sms_code").getAsInt());

            return submitPhoneNumberResponse;
        }
    }

    public static class SubmitPhoneNumberRequestJsonDeserializer implements JsonDeserializer<SubmitPhoneNumberRequest> {

        @Override
        public SubmitPhoneNumberRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String phoneNumber = json.getAsJsonObject().get("phone_number").getAsString();
            String promoCode = json.getAsJsonObject().get("promo_code").getAsString();

            return new SubmitPhoneNumberRequest(phoneNumber, promoCode);
        }
    }

    public static class City {

        private Long id;
        private String name;

        public City(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }

    public static class CityJsonDeserializer implements JsonDeserializer<City> {

        @Override
        public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Long id = json.getAsJsonObject().get("city_id").getAsLong();
            String name = json.getAsJsonObject().get("city_name").getAsString();

            return new City(id, name);
        }
    }

    public static class Cities {

        private List<City> cities;

        public Cities(List<City> cities) {
            this.cities = new ArrayList<>(cities);
        }

        public List<City> getCities() {
            return new ArrayList<>(cities);
        }

    }

    public static class CitiesJsonDeserializer implements JsonDeserializer<Cities> {

        @Override
        public Cities deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<City> cities = new ArrayList<>();

            for (int count = 0; count < json.getAsJsonObject().getAsJsonArray("cities").size(); count++) {
                cities.add(context.<City>deserialize(json.getAsJsonObject().getAsJsonArray("cities").get(count), City.class));
            }

            return new Cities(cities);
        }
    }

    private Call<Cities> callQueryCities;
    private Cities cities = new Cities(Collections.<City>emptyList());

    private class CitiesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cities.getCities().size();
        }

        @Override
        public City getItem(int position) {
            return cities.getCities().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CityViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_2, parent, false);

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

    private CitiesAdapter citiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        citiesAdapter = new CitiesAdapter();

        ListView citiesView = (ListView) findViewById(R.id.cities);
        citiesView.setAdapter(citiesAdapter);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Cities.class, new CitiesJsonDeserializer())
                .registerTypeAdapter(City.class, new CityJsonDeserializer())
                .registerTypeAdapter(SubmitPhoneNumberRequest.class, new SubmitPhoneNumberRequestJsonDeserializer())
                .registerTypeAdapter(SubmitPhoneNumberResponse.class, new SubmitPhoneNumberResponseJsonDeserializer())
                .serializeNulls()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://beta.taxistock.ru/taxik/api/client/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TaxikService taxikService = retrofit.create(TaxikService.class);

        callQueryCities = taxikService.queryCities();
        callQueryCities.enqueue(new Callback<Cities>() {

            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                cities = response.body();
                citiesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Update cities error", Toast.LENGTH_LONG).show();
            }
        });

        SubmitPhoneNumberRequest request = new SubmitPhoneNumberRequest("+79167749891", "");

        Call<SubmitPhoneNumberResponse> callQuerySubmitPhoneNumber = taxikService.querySubmitPhoneNumber(request);
        callQuerySubmitPhoneNumber.enqueue(new Callback<SubmitPhoneNumberResponse>() {

            @Override
            public void onResponse(Call<SubmitPhoneNumberResponse> call, Response<SubmitPhoneNumberResponse> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<SubmitPhoneNumberResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Call QuerySubmitPhoneNumber error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        callQueryCities.cancel();
    }
}
