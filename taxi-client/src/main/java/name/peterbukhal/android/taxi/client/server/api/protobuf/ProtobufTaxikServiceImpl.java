package name.peterbukhal.android.taxi.client.server.api.protobuf;

import retrofit2.Retrofit;
import retrofit2.converter.protobuf.ProtoConverterFactory;

/**
 * Created by
 *      petronic on 13.05.16.
 */
public final class ProtobufTaxikServiceImpl {

    private static ProtobufTaxikServiceImpl sInstance;
    private final ProtobufTaxikService mService;

    private ProtobufTaxikServiceImpl() {
        mService = new Retrofit.Builder()
                .baseUrl("http://beta.taxistock.ru/taxik/api/client/")
                .addConverterFactory(ProtoConverterFactory.create())
                .build()
                .create(ProtobufTaxikService.class);
    }

    public static ProtobufTaxikServiceImpl instance() {
        if (sInstance == null) {
            sInstance = new ProtobufTaxikServiceImpl();
        }

        return sInstance;
    }

    public synchronized ProtobufTaxikService service() {
        return mService;
    }

}
