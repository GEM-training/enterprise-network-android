package gem.training3.enterprisenetwork.screen.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import butterknife.Bind;
import butterknife.OnClick;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseActivity;
import gem.training3.enterprisenetwork.common.util.DeviceUtils;
import gem.training3.enterprisenetwork.common.util.DialogUtils;
import gem.training3.enterprisenetwork.common.util.NetworkUtils;
import gem.training3.enterprisenetwork.screen.main.MainActivity;

/**
 * Created by huylv on 22/02/2016.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Bind(R.id.login_email_et)
    EditText etLoginEmail;

    @Bind(R.id.login_password_et)
    EditText etLoginPassword;

    @Bind(R.id.pbLogin)
    ProgressBar pbLogin;

    @Bind(R.id.login_form_sv)
    ScrollView svLoginForm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onPasswordError() {
        DialogUtils.showErrorAlert(this, "Password too short!");
    }

    @Override
    public void onLoginSuccess() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onRequestError(String errorMessage) {
        pbLogin.setVisibility(View.GONE);
        svLoginForm.setVisibility(View.VISIBLE);
        DialogUtils.showErrorAlert(this,errorMessage);
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenterImpl(this);
    }

    @OnClick(R.id.login_login_bt)
    public void doLogin() {
        if(!NetworkUtils.networkConnected(this)){
            DialogUtils.showErrorAlert(this, getString(R.string.dialog_title_content_network_problem));
        }else {
            //just for testing
            etLoginEmail.setText("huylv");
            etLoginPassword.setText("123456");

            getPresenter().doLogin(this, etLoginEmail.getText().toString(),
                    etLoginPassword.getText().toString(), DeviceUtils.getDeviceId(this));
            pbLogin.setVisibility(View.VISIBLE);
            svLoginForm.setVisibility(View.GONE);
        }
    }
}
