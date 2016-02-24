package gem.training3.enterprisenetwork.screen.welcome;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseActivity;
import gem.training3.enterprisenetwork.base.BasePresenter;
import gem.training3.enterprisenetwork.screen.login.LoginActivity;

/**
 * Created by huylv on 24/02/2016.
 */
public class WelcomeActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.welcome_signup_customer)
    void signupCustomer(){

    }

    @OnClick(R.id.welcome_signin)
    void startSigninActivity(){
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }
}
