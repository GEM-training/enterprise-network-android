package gem.training3.enterprisenetwork.screen.welcome;

import android.content.Intent;

import butterknife.OnClick;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseActivity;
import gem.training3.enterprisenetwork.base.BasePresenter;
import gem.training3.enterprisenetwork.base.log.EventLogger;
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
        EventLogger.info("Start sign in activity");
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        EventLogger.info("Exit application");
        super.onBackPressed();
    }
}
