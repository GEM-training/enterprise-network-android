package gem.training3.enterprisenetwork.screen.login;

import android.app.Activity;

import gem.training3.enterprisenetwork.common.util.NetworkUtils;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.callback.BaseCallback;
import gem.training3.enterprisenetwork.network.dto.ResponseDTO;
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

        if(!NetworkUtils.networkConnected(context)){
            mView.onNetworkError();
            return;
        }
//        if (!StringUtils.validateEmail(email)) {
//            mView.onEmailError();
//            return;
//        }

        if (password.length() < 3) {
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

            mView.onRequestSuccess();
            mView.onLoginSuccess(response);
        }

    };
}
