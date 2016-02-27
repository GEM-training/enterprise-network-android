package gem.training3.enterprisenetwork.network.callback;

import gem.training3.enterprisenetwork.network.model.ResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Base callback for all services
 * Created by neo on 2/15/2016.
 */
public abstract class BaseCallback implements Callback<ResponseDTO> {
    public static final int NETWORK_ERROR = 999;

    @Override
    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
        // Get body of request
        ResponseDTO body = response.body();
        int statusCode = body.getStatusCode();

        switch (statusCode){
            case 200:
            case 201:
                onResponse(response);
                break;
            default:
                onError(statusCode,body.getMessage());
                break;
        }

    }

    @Override
    public void onFailure(Call<ResponseDTO> call, Throwable t) {
        onError(NETWORK_ERROR, t.getMessage());
    }

    public abstract void onError(int errorCode, String errorMessage);
    public abstract void onResponse(Response<ResponseDTO> response);

}
