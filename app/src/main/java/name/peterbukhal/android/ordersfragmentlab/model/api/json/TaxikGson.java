package name.peterbukhal.android.ordersfragmentlab.model.api.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import name.peterbukhal.android.ordersfragmentlab.model.Cities;
import name.peterbukhal.android.ordersfragmentlab.model.Order;
import name.peterbukhal.android.ordersfragmentlab.model.impl.CityImpl;
import name.peterbukhal.android.ordersfragmentlab.model.impl.OrderImpl;
import name.peterbukhal.android.ordersfragmentlab.model.Orders;
import name.peterbukhal.android.ordersfragmentlab.model.api.Error;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.QueryOrdersRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitSmsCodeResponse;
import name.peterbukhal.android.ordersfragmentlab.service.TaxikService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public final class TaxikGson {

    private TaxikGson() {}

    public static TaxikService service() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Error.class, new ErrorSerializer())
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .registerTypeAdapter(Cities.class, new CitiesSerializer())
                .registerTypeAdapter(Cities.class, new CitiesDeserializer())
                .registerTypeAdapter(CityImpl.class, new CitySerializer())
                .registerTypeAdapter(CityImpl.class, new CityDeserializer())
                .registerTypeAdapter(SubmitPhoneNumberRequest.class, new SubmitPhoneNumberRequestSerializer())
                .registerTypeAdapter(SubmitPhoneNumberRequest.class, new SubmitPhoneNumberRequestDeserializer())
                .registerTypeAdapter(SubmitPhoneNumberResponse.class, new SubmitPhoneNumberResponseSerializer())
                .registerTypeAdapter(SubmitPhoneNumberResponse.class, new SubmitPhoneNumberResponseDeserializer())
                .registerTypeAdapter(SubmitSmsCodeRequest.class, new SubmitSmsCodeRequestSerializer())
                .registerTypeAdapter(SubmitSmsCodeRequest.class, new SubmitSmsCodeRequestDeserializer())
                .registerTypeAdapter(SubmitSmsCodeResponse.class, new SubmitSmsCodeResponseSerializer())
                .registerTypeAdapter(SubmitSmsCodeResponse.class, new SubmitSmsCodeResponseDeserializer())
//                .registerTypeAdapter(QueryOrdersRequest.class, new QueryOrdersRequestSerializer())
                .registerTypeAdapter(Orders.class, new OrdersDeserializer())
                .registerTypeAdapter(Order.class, new OrderDeserializer())
                .serializeNulls()
                .create();

        return new Retrofit.Builder()
                .baseUrl("http://beta.taxistock.ru/taxik/api/client/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(TaxikService.class);
    }

}
