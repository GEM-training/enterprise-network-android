package gem.training3.enterprisenetwork.screen.welcome;

import android.content.Intent;

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
    public BasePresenter onCreatePresenter() {
        return null;
    }

    @OnClick(R.id.welcome_signin)
    void startSignInActivity(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
