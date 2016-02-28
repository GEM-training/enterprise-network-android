package gem.training3.enterprisenetwork.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huylv on 22/02/2016.
 */
public class ServiceBuilder {
    private static final String BASE_URL = "http://192.168.0.3:8080";

    private static Retrofit sInstance;
    private static APIService sService;

    private static Retrofit getRetrofit() {
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance;
    }

    public static APIService getService() {
        if (sService == null) {
            sService = getRetrofit().create(APIService.class);
        }

        return sService;
    }
}
