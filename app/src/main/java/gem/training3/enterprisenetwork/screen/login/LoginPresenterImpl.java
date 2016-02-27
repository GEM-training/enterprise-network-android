package gem.training3.enterprisenetwork.screen.login;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.common.util.VarUtils;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.callback.BaseCallback;
import gem.training3.enterprisenetwork.network.model.ResponseDTO;
import gem.training3.enterprisenetwork.network.model.ResponseUserInfoDTO;
import gem.training3.enterprisenetwork.network.model.UserInfoDTO;
import retrofit2.Response;

/**
 * Created by huylv on 22/02/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private final LoginView mView;

    public LoginPresenterImpl(LoginView view) {
        mView = view;
    }

    @Override
    public void doLogin(Activity context, String email, String password, String deviceId) {
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
    private final BaseCallback mLoginCallback = new BaseCallback() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            mView.onRequestError(errorMessage);
        }

        @Override
        public void onResponse(Response<ResponseDTO> response) {

            VarUtils.LOGGED_IN = true;
            //put user info to intent

            Gson gson = new Gson();
            ResponseDTO responseDTO = response.body();
            ResponseUserInfoDTO responseUserInfo = gson.fromJson(gson.toJson(responseDTO.getReturnObject()), ResponseUserInfoDTO.class);
            Session.setUser(responseUserInfo);

            String json = gson.toJson(responseUserInfo);
            Activity context = (Activity) mView;
            //save to SP
            SharedPreferences sp = context.getSharedPreferences(Constants.USER_INFO, Activity.MODE_PRIVATE);
            sp.edit().putString(Constants.SPKEY_USERJSON, json).apply();
            Log.e("cxz",json);
            mView.onLoginSuccess();
        }

    };
}
