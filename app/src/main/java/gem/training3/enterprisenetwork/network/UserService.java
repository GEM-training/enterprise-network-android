package gem.training3.enterprisenetwork.network;

import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.dto.Product;
import gem.training3.enterprisenetwork.network.dto.ResponseDTO;
import gem.training3.enterprisenetwork.network.dto.ResponseStore;
import gem.training3.enterprisenetwork.network.dto.UserInfoDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by huylv on 22/02/2016.
 */
public interface UserService {
    @Headers("Content-Type:application/json")
    @POST("/login")
    Call<ResponseDTO> login(@Body UserInfoDTO user);

    @GET("/logoutuser")
    Call<ResponseDTO> logout(@Header(Constants.token) String access_token);

    @GET("/store")
    Call<ResponseStore> getAllStore(@Header(Constants.token) String access_token );

    @GET("/store/{id}/product")
    Call<Product[]> getProductByStore(@Header(Constants.token) String access_token, @Path("id") int id);
}
