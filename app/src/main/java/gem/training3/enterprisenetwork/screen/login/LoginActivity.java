package gem.training3.enterprisenetwork.screen.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.OnClick;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseActivity;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.common.util.DeviceUtils;
import gem.training3.enterprisenetwork.common.util.DialogUtils;
import gem.training3.enterprisenetwork.common.util.NetworkUtils;
import gem.training3.enterprisenetwork.common.util.VarUtils;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.dto.ResponseDTO;
import gem.training3.enterprisenetwork.network.dto.ResponseUserInfoDTO;
import gem.training3.enterprisenetwork.screen.main.MainActivity;
import retrofit2.Response;

/**
 * Created by huylv on 22/02/2016.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Bind(R.id.etLoginEmail)
    EditText etLoginEmail;

    @Bind(R.id.etLoginPassword)
    EditText etLoginPassword;

    @Bind(R.id.pbLogin)
    ProgressBar pbLogin;

    @Bind(R.id.svLoginForm)
    ScrollView svLoginForm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onEmailError() {

    }

    @Override
    public void onPasswordError() {
        DialogUtils.showErrorAlert(this, "Password too short!");
    }

    @Override
    public void onLoginSuccess(Response<ResponseDTO> response) {
        VarUtils.LOGGED_IN = true;
        //put user info to intent
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        Gson gson = new Gson();
        ResponseDTO responseDTO = (ResponseDTO) response.body();

        ResponseUserInfoDTO responseUserInfo = gson.fromJson(gson.toJson(responseDTO.getReturnObject()), ResponseUserInfoDTO.class);

        Session.setUser(responseUserInfo);

        String json = gson.toJson(responseUserInfo);
        Log.e("cxz", json);

        //save to SP
        SharedPreferences sp = getSharedPreferences(Constants.USER_INFO, MODE_PRIVATE);
        sp.edit().putString(Constants.SPKEY_USERJSON, json).commit();
        startActivity(i);
        finish();
    }

    @Override
    public void onNetworkError() {
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btLogin)
    public void doLogin() {
        if(!NetworkUtils.networkConnected(this)){
            DialogUtils.showErrorAlert(this, "Network error!");
        }else {
            getPresenter().doLogin(this, etLoginEmail.getText().toString(),
                    etLoginPassword.getText().toString(), DeviceUtils.getDeviceId(this));
            pbLogin.setVisibility(View.VISIBLE);
            svLoginForm.setVisibility(View.GONE);
        }
    }
}
