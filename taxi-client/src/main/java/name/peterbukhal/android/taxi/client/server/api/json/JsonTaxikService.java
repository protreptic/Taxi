package name.peterbukhal.android.taxi.client.server.api.json;

import name.peterbukhal.android.taxi.client.model.Cities;
import name.peterbukhal.android.taxi.client.model.Order;
import name.peterbukhal.android.taxi.client.model.Orders;
import name.peterbukhal.android.taxi.client.model.UserInfo;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryAvailableDriversRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryCitiesRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryDriverLocationRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrderDetailsRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrderStateRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryOrdersRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryPriceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryTaxiLocationsRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.QueryUserInfoRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.RegisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitInviteFriendRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitOrderCancelRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitOrderCreateRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitOrderRateRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPhoneNumberRequest;
import name.peterbukhal.android.taxi.client.server.api.json.request.UnregisterDeviceRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.QueryAvailableDriversResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.QueryDriverLocationResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.QueryOrderStateResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.QueryPriceResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.QueryTaxiLocationsResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.RegisterDeviceResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitInviteFriendResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitOrderCancelResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitOrderCreateResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitOrderRateResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPhoneNumberResponse;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitPromoCodeRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitPromoCodeResponse;
import name.peterbukhal.android.taxi.client.server.api.json.request.SubmitSmsCodeRequest;
import name.peterbukhal.android.taxi.client.server.api.json.response.SubmitSmsCodeResponse;
import name.peterbukhal.android.taxi.client.server.api.json.response.UnregisterDeviceResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public interface JsonTaxikService {

    String BASE_URL = "http://beta.taxistock.ru/taxik/api/client/";
    int API_VERSION = 2236;

    /**
     * Получение списка городов.
     *
     * @param request
     * @return
     */
    @POST("query_cities")
    Call<Cities> queryCities(@Body QueryCitiesRequest request);

    /**
     * Клиент отправляет на сервер номер телефона. В ответ получает SMS с кодом
     * подтверждения.
     *
     * @param request
     * @return
     */
    @POST("submit_phone_number")
    Call<SubmitPhoneNumberResponse> submitPhoneNumber(@Body SubmitPhoneNumberRequest request);

    /**
     * Клиент отправляет на сервер код подтверждения. В ответ получает
     * долгосрочный token для авторизации в системе.
     *
     * @param request
     * @return
     */
    @POST("submit_sms_code")
    Call<SubmitSmsCodeResponse> submitSmsCode(@Body SubmitSmsCodeRequest request);

    /**
     * Промо-код - это набор из нескольких цифр, позволяющий партнеру
     * использовать дополнительные бонусы в системе (бонусы - TBD, вне клиентского
     * приложения "Таксик" и касаются маркетинговой политики клиента).
     * Последние 4 цифры будут означать время регистрации партнера, первые несколько цифр -
     * его ID. Допустим, партнер с ID 1234 был зарегистрирован в 57:25 (57 минут 25 сек), тогда
     * его промо-код будет 12345725. Если клиент вводит этот код, то все оформленные им
     * заказы будут передаваться на наш сервер с id 1234 и партнеру будет зачисляться комиссия
     * за этот заказ.
     *
     *      • минимальное количество символов - 5
     *      • максимальное количество символов - 8
     *
     * @param request
     * @return
     *
     * @deprecated Оставлен для совместимости и всегда возвращает 'status': true,
     * никаких действий не производится.
     */
    @SuppressWarnings("unused")
    @Deprecated
    @POST("submit_promo_code")
    Call<SubmitPromoCodeResponse> submitPromoCode(@Body SubmitPromoCodeRequest request);

    /**
     * Получение информации о клиенте.
     *
     * @param request
     * @return
     */
    @POST("query_user_info")
    Call<UserInfo> queryUserInfo(@Body QueryUserInfoRequest request);

    /**
     * Получение списка заказов клиента.
     *
     * @param request
     * @return
     */
    @POST("query_orders")
    Call<Orders> queryOrders(@Body QueryOrdersRequest request);

    /**
     * Получение подробной информации о заказе по его уникальному
     * идентификатору.
     *
     * @param request
     * @return
     */
    @POST("query_order_details")
    Call<Order> queryOrderDetails(@Body QueryOrderDetailsRequest request);

    /**
     * Получение информации о местонахождении машин такси для отображения на
     * карте.
     *
     * @param request
     * @return
     */
    @POST("query_taxi_locations")
    Call<QueryTaxiLocationsResponse> queryTaxiLocations(@Body QueryTaxiLocationsRequest request);

    /**
     * Пользователь отправляет на сервер информацию о предпочитаемых
     * параметрах заказа.
     *
     * @param request
     * @return
     */
    @POST("submit_order_create")
    Call<SubmitOrderCreateResponse> submitOrderCreate(@Body SubmitOrderCreateRequest request);

    /**
     * После создания заказа, приложение ожидает от сервера Push Notification (0)
     * если есть хотя бы 1 водитель, который принял заказ. Если время поиска водителя истекло
     * – сервер должен пометить данный заказ как отмененный и отправить Push Notification (1)
     * (относится только к срочным заказам).
     *
     * @param request
     * @return
     */
    @POST("query_available_drivers")
    Call<QueryAvailableDriversResponse> queryAvailableDrivers(@Body QueryAvailableDriversRequest request);

    /**
     * У пользователя есть возможность отменить созданный (текущий или
     * запланированный) заказ.
     *
     * @param request
     * @return
     */
    @POST("submit_order_cancel")
    Call<SubmitOrderCancelResponse> submitOrderCancel(@Body SubmitOrderCancelRequest request);

    /**
     * После подтверждения заказа (выбора водителя), приложение каждые 30 сек.
     * получает информацию о местоположении выбранного водителя.
     * По прибытию водителя на место подачи, сервер отправляет клиенту Push Notification (2).
     * Так же данный метод вызывается каждые 30 сек. после посадки пассажира для
     * отслеживания местоположения машины во время поездки.
     *
     * Если водитель задерживается, флаг delayed устанавливается в true.
     *
     * @param request
     * @return
     */
    @POST("query_driver_location")
    Call<QueryDriverLocationResponse> queryDriverLocation(@Body QueryDriverLocationRequest request);

    /**
     * После прибытия машины на место назначения, приложение получает от
     * сервера Push Notification (3) и затем загружает информацию о заказе.
     *
     * @param request
     * @return
     */
    @POST("query_order_state")
    Call<QueryOrderStateResponse> queryOrderState(@Body QueryOrderStateRequest request);

    /**
     * У пользователя есть возможность оценить поездку после оплаты заказа.
     *
     * @param request
     * @return
     */
    @POST("submit_order_rate")
    Call<SubmitOrderRateResponse> submitOrderRate(@Body SubmitOrderRateRequest request);

    /**
     * Получение информации о примерной стоимости заказа.
     *
     * @param request
     * @return
     */
    @POST("query_price")
    Call<QueryPriceResponse> queryPrice(@Body QueryPriceRequest request);

    @POST("register_device")
    Call<RegisterDeviceResponse> registerDevice(@Body RegisterDeviceRequest request);

    @POST("unregister_device")
    Call<UnregisterDeviceResponse> unregisterDevice(@Body UnregisterDeviceRequest request);



    @POST("submit_invite_friend")
    Call<SubmitInviteFriendResponse> submitInviteFriend(@Body SubmitInviteFriendRequest request);

}
