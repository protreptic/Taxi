package name.peterbukhal.android.taxi.client.server.api.json;

import name.peterbukhal.android.taxi.client.model.Cities;
import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.Orders;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrderDetailsRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrdersRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryUserInfoRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.UnregisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.RegisterDeviceResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPromoCodeRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPromoCodeResponse;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitSmsCodeResponse;
import name.peterbukhal.android.taxi.client.model.impl.UserInfoImpl;
import name.peterbukhal.android.taxi.client.server.api.json.response.UnregisterDeviceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public interface JsonTaxikService {

    @POST("query_cities")
    Call<Cities> queryCities();

    @POST("submit_phone_number")
    Call<SubmitPhoneNumberResponse> querySubmitPhoneNumber(@Body SubmitPhoneNumberRequest request);

    @POST("submit_sms_code")
    Call<SubmitSmsCodeResponse> querySubmitSmsCode(@Body SubmitSmsCodeRequest request);

    // TODO Not implemented yet
    @POST("submit_promo_code")
    Call<SubmitPromoCodeResponse> querySubmitPromoCode(@Body SubmitPromoCodeRequest request);

    // TODO Not implemented yet
    @POST("query_user_info")
    Call<UserInfoImpl> queryUserInfo(@Body QueryUserInfoRequest request);

    @POST("query_orders")
    Call<Orders> queryOrders(@Body QueryOrdersRequest request);

    @POST("query_order_details")
    Call<Order> queryOrderDetails(@Body QueryOrderDetailsRequest request);

    @POST("register_device")
    Call<RegisterDeviceResponse> queryRegisterDevice(@Body RegisterDeviceRequest request);

    @POST("unregister_device")
    Call<UnregisterDeviceResponse> queryUnregisterDevice(@Body UnregisterDeviceRequest request);

}
