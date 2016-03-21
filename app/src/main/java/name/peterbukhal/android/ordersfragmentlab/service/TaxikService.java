package name.peterbukhal.android.ordersfragmentlab.service;

import name.peterbukhal.android.ordersfragmentlab.model.Cities;
import name.peterbukhal.android.ordersfragmentlab.model.Orders;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.QueryOrdersRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.QueryUserInfoRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitPromoCodeRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitPromoCodeResponse;
import name.peterbukhal.android.ordersfragmentlab.model.api.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.ordersfragmentlab.model.api.response.SubmitSmsCodeResponse;
import name.peterbukhal.android.ordersfragmentlab.model.UserInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public interface TaxikService {

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
    Call<UserInfo> queryUserInfo(@Body QueryUserInfoRequest request);

    @POST("query_orders")
    Call<Orders> queryOrders(@Body QueryOrdersRequest request);

}
