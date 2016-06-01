package name.peterbukhal.android.taxi.client.server.api.protobuf;

import name.peterbukhal.android.taxi.client.model.Cities;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by
 *      petronic on 10.05.16.
 */
public interface ProtobufTaxikService {

    @POST("query_cities")
    Call<Cities> queryCities();

}
