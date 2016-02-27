package gem.training3.enterprisenetwork.network;

import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.model.ResponseDTO;
import gem.training3.enterprisenetwork.network.model.ResponseProduct;
import gem.training3.enterprisenetwork.network.model.ResponseStore;
import gem.training3.enterprisenetwork.network.model.UserInfoDTO;
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
    @POST("/login")
    Call<ResponseDTO> login(@Body UserInfoDTO user);

    @GET("/logoutuser")
    Call<ResponseDTO> logout(@Header(Constants.token) String access_token);

    @GET("/store")
    Call<ResponseStore> getStore(@Header(Constants.token) String access_token,@Query("page") int page,@Query("size") int size);

    @GET("/store/{id}/product")
    Call<ResponseProduct> getProductByStore(@Header(Constants.token) String access_token, @Path("id") Long id,@Query("page") int page,@Query("size") int size);
}
