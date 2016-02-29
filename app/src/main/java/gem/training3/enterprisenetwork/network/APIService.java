package gem.training3.enterprisenetwork.network;

import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.model.ResponseProduct;
import gem.training3.enterprisenetwork.network.model.ResponseStore;
import gem.training3.enterprisenetwork.network.model.UserCredential;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by huylv on 22/02/2016.
 */
public interface APIService {
    @Headers("Content-Type:application/json")
    @POST("authenticate/login")
    Call<UserCredential> login(@Body UserCredential user);

    @POST("authenticate/logout")
    Call<Void> logout(@Header(Constants.token) String access_token,@Header(Constants.deviceId) String deviceId);

    @GET("/store")
    Call<ResponseStore> getStore(@Header(Constants.token) String access_token,
                                 @Header(Constants.deviceId) String deviceId,
                                 @Query("page") int page,
                                 @Query("size") int size,
                                 @Query("sort") String sort
    );

    @GET("/store/{id}/product")
    Call<ResponseProduct> getProductByStore(@Header(Constants.token) String access_token,
                                            @Header(Constants.deviceId) String deviceId,
                                            @Path("id") Long storeId,
                                            @Query("page") int page,
                                            @Query("size") int size,
                                            @Query("sort") String sort
    );
}
