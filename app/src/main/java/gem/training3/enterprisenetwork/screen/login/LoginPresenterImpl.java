package gem.training3.enterprisenetwork.screen.login;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import gem.training3.enterprisenetwork.base.log.EventLogger;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.common.util.VarUtils;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.model.UserCredential;
import retrofit2.Call;
import retrofit2.Callback;
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
                .login(new UserCredential(email, password,null, deviceId))
                .enqueue(new Callback<UserCredential>() {
                    @Override
                    public void onResponse(Call<UserCredential> call, Response<UserCredential> response) {
                        VarUtils.LOGGED_IN = true;

                        Gson gson = new Gson();
                        UserCredential user = response.body();

                        Session.setUser(user);

                        String json = gson.toJson(user);
                        Activity context = (Activity) mView;
                        //save to SP
                        SharedPreferences sp = context.getSharedPreferences(Constants.USER_INFO, Activity.MODE_PRIVATE);
                        sp.edit().putString(Constants.SHARE_PREFERENCE_KEY_USER_JSON, json).apply();
                        EventLogger.info("Log in successful, user: "+user);
                        mView.onLoginSuccess();
                    }

                    @Override
                    public void onFailure(Call<UserCredential> call, Throwable t) {
                        mView.onRequestError(t.getMessage());
                    }
                });
    }
}
