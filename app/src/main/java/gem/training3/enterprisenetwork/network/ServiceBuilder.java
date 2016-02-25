package gem.training3.enterprisenetwork.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huylv on 22/02/2016.
 */
public class ServiceBuilder {
    private static final String BASE_URL = "http://172.16.10.70:8080";

    private static Retrofit sInstance;
    private static UserService sService;

    private static Retrofit getRetrofit() {
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sInstance;
    }

    public static UserService getService() {
        if (sService == null) {
            sService = getRetrofit().create(UserService.class);
        }

        return sService;
    }
}
