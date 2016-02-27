package gem.training3.enterprisenetwork.screen.fragment.welcome;

import android.widget.TextView;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseFragment;
import gem.training3.enterprisenetwork.network.model.ResponseUserInfoDTO;

/**
 * Created by huylv on 24/02/2016.
 */
public class WelcomeFragment extends BaseFragment<WelcomePresenter> implements WelcomeView {

    @Bind(R.id.welcome_email_tv)
    TextView welcome_email_tv;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welcome;
    }

    @Override
    public WelcomePresenter onCreatePresenter() {
        return new WelcomePresenterImpl(this);
    }


    @Override
    public void onGetCurrentUserSuccess(ResponseUserInfoDTO u) {
        welcome_email_tv.setText(u.getUsername());
    }

}
