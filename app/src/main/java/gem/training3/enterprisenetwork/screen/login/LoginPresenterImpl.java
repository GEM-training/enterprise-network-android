package gem.training3.enterprisenetwork.screen.login;

import android.app.Activity;

import com.google.gson.Gson;

import gem.training3.enterprisenetwork.common.util.VarUtils;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.callback.BaseCallback;
import gem.training3.enterprisenetwork.network.dto.ResponseDTO;
import gem.training3.enterprisenetwork.network.dto.ResponseUserInfoDTO;
import gem.training3.enterprisenetwork.network.dto.UserInfoDTO;
import retrofit2.Response;

/**
 * Created by huylv on 22/02/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private LoginView mView;

    public LoginPresenterImpl(LoginView view) {
        mView = view;
    }

    @Override
    public void doLogin(Activity context,String email, String password,String deviceId) {

        if (password.length() < 5) {
            mView.onPasswordError();
            return;
        }

        ServiceBuilder.getService()
                .login(new UserInfoDTO(email, password, deviceId))
                .enqueue(mLoginCallback);
    }

    /**
     * Login request callback
     */
    private BaseCallback mLoginCallback = new BaseCallback() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            mView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Response<ResponseDTO> response) {
            VarUtils.LOGGED_IN = true;

            Gson gson = new Gson();
            ResponseDTO responseDTO = (ResponseDTO) response.body();

            ResponseUserInfoDTO responseUserInfo = gson.fromJson(gson.toJson(responseDTO.getReturnObject()), ResponseUserInfoDTO.class);

            Session.setUser(responseUserInfo);

            String json = gson.toJson(responseUserInfo);

            mView.onRequestSuccess();
            mView.onLoginSuccess(json);
        }

    };
}
