package name.peterbukhal.android.taxi.client.server.api.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import name.peterbukhal.android.taxi.client.model.Cities;
import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.model.Conditions;
import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.Orders;
import name.peterbukhal.android.taxi.client.model.Point;
import name.peterbukhal.android.taxi.client.model.RoutePoint;
import name.peterbukhal.android.taxi.client.model.SpnPoint;
import name.peterbukhal.android.taxi.client.model.Tariff;
import name.peterbukhal.android.taxi.client.server.api.json.model.CitiesDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.CitiesSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.CityDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.CitySerializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.ConditionsDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.ConditionsSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.ErrorDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.ErrorSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.OrderDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.OrdersDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.PointDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.PointSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.RoutePointDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.RoutePointSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.SpnPointDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.SpnPointSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.TariffDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.model.TariffSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.EmptyRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.EmptyRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryCitiesRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryCitiesRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrderDetailsRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrderDetailsRequestDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrderDetailsRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrdersRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrdersRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryPriceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryPriceRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequestDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitInviteFriendRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitInviteFriendRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitOrderCreateRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitOrderCreateRequestDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitOrderCreateRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPhoneNumberRequestDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPhoneNumberRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPromoCodeRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPromoCodeRequestDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPromoCodeRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitSmsCodeRequestDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitSmsCodeRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.UnregisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.UnregisterDeviceRequestDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.request.UnregisterDeviceRequestSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.QueryPriceResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.QueryPriceResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.RegisterDeviceResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.RegisterDeviceResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.RegisterDeviceResponseSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitInviteFriendResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitInviteFriendResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitOrderCreateResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitOrderCreateResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitOrderCreateResponseSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPhoneNumberResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPhoneNumberResponseSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPromoCodeResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPromoCodeResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPromoCodeResponseSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitSmsCodeResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitSmsCodeResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitSmsCodeResponseSerializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.UnregisterDeviceResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.UnregisterDeviceResponseDeserializer;
import name.peterbukhal.android.taxi.client.server.api.json.response.UnregisterDeviceResponseSerializer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public final class JsonTaxikServiceImpl {

    private static JsonTaxikServiceImpl sInstance;
    private final JsonTaxikService mService;

    private JsonTaxikServiceImpl() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Error.class, new ErrorSerializer())
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .registerTypeAdapter(Cities.class, new CitiesSerializer())
                .registerTypeAdapter(Cities.class, new CitiesDeserializer())
                .registerTypeAdapter(City.class, new CitySerializer())
                .registerTypeAdapter(City.class, new CityDeserializer())
                .registerTypeAdapter(SubmitPhoneNumberRequest.class, new SubmitPhoneNumberRequestSerializer())
                .registerTypeAdapter(SubmitPhoneNumberRequest.class, new SubmitPhoneNumberRequestDeserializer())
                .registerTypeAdapter(SubmitPhoneNumberResponse.class, new SubmitPhoneNumberResponseSerializer())
                .registerTypeAdapter(SubmitPhoneNumberResponse.class, new SubmitPhoneNumberResponseDeserializer())
                .registerTypeAdapter(SubmitSmsCodeRequest.class, new SubmitSmsCodeRequestSerializer())
                .registerTypeAdapter(SubmitSmsCodeRequest.class, new SubmitSmsCodeRequestDeserializer())
                .registerTypeAdapter(SubmitSmsCodeResponse.class, new SubmitSmsCodeResponseSerializer())
                .registerTypeAdapter(SubmitSmsCodeResponse.class, new SubmitSmsCodeResponseDeserializer())
                .registerTypeAdapter(QueryOrdersRequest.class, new QueryOrdersRequestSerializer())
                .registerTypeAdapter(Orders.class, new OrdersDeserializer())
                .registerTypeAdapter(Order.class, new OrderDeserializer())
                .registerTypeAdapter(RoutePoint.class, new RoutePointSerializer())
                .registerTypeAdapter(RoutePoint.class, new RoutePointDeserializer())
                .registerTypeAdapter(Point.class, new PointSerializer())
                .registerTypeAdapter(Point.class, new PointDeserializer())
                .registerTypeAdapter(SubmitPromoCodeRequest.class, new SubmitPromoCodeRequestSerializer())
                .registerTypeAdapter(SubmitPromoCodeRequest.class, new SubmitPromoCodeRequestDeserializer())
                .registerTypeAdapter(SubmitPromoCodeResponse.class, new SubmitPromoCodeResponseSerializer())
                .registerTypeAdapter(SubmitPromoCodeResponse.class, new SubmitPromoCodeResponseDeserializer())
                .registerTypeAdapter(QueryOrderDetailsRequest.class, new QueryOrderDetailsRequestSerializer())
                .registerTypeAdapter(QueryOrderDetailsRequest.class, new QueryOrderDetailsRequestDeserializer())
                .registerTypeAdapter(RegisterDeviceRequest.class, new RegisterDeviceRequestSerializer())
                .registerTypeAdapter(RegisterDeviceRequest.class, new RegisterDeviceRequestDeserializer())
                .registerTypeAdapter(RegisterDeviceResponse.class, new RegisterDeviceResponseSerializer())
                .registerTypeAdapter(RegisterDeviceResponse.class, new RegisterDeviceResponseDeserializer())
                .registerTypeAdapter(UnregisterDeviceRequest.class, new UnregisterDeviceRequestSerializer())
                .registerTypeAdapter(UnregisterDeviceRequest.class, new UnregisterDeviceRequestDeserializer())
                .registerTypeAdapter(UnregisterDeviceResponse.class, new UnregisterDeviceResponseSerializer())
                .registerTypeAdapter(UnregisterDeviceResponse.class, new UnregisterDeviceResponseDeserializer())
                .registerTypeAdapter(Tariff.class, new TariffSerializer())
                .registerTypeAdapter(Tariff.class, new TariffDeserializer())
                .registerTypeAdapter(Conditions.class, new ConditionsSerializer())
                .registerTypeAdapter(Conditions.class, new ConditionsDeserializer())
                .registerTypeAdapter(SubmitOrderCreateRequest.class, new SubmitOrderCreateRequestSerializer())
                .registerTypeAdapter(SubmitOrderCreateRequest.class, new SubmitOrderCreateRequestDeserializer())
                .registerTypeAdapter(SubmitOrderCreateResponse.class, new SubmitOrderCreateResponseSerializer())
                .registerTypeAdapter(SubmitOrderCreateResponse.class, new SubmitOrderCreateResponseDeserializer())
                .registerTypeAdapter(SpnPoint.class, new SpnPointSerializer())
                .registerTypeAdapter(SpnPoint.class, new SpnPointDeserializer())
                .registerTypeAdapter(QueryCitiesRequest.class, new QueryCitiesRequestSerializer())
                .registerTypeAdapter(EmptyRequest.class, new EmptyRequestSerializer())
                .registerTypeAdapter(SubmitInviteFriendRequest.class, new SubmitInviteFriendRequestSerializer())
                .registerTypeAdapter(SubmitInviteFriendResponse.class, new SubmitInviteFriendResponseDeserializer())
                .registerTypeAdapter(QueryPriceRequest.class, new QueryPriceRequestSerializer())
                .registerTypeAdapter(QueryPriceResponse.class, new QueryPriceResponseDeserializer())
                .serializeNulls()
                .create();

        mService = new Retrofit.Builder()
                .baseUrl(JsonTaxikService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(JsonTaxikService.class);
    }

    public static JsonTaxikServiceImpl instance() {
        if (sInstance == null) {
            sInstance = new JsonTaxikServiceImpl();
        }

        return sInstance;
    }

    public synchronized JsonTaxikService service() {
        return mService;
    }

}
