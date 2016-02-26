package gem.training3.enterprisenetwork.screen.fragment.welcome;

import gem.training3.enterprisenetwork.network.Session;

/**
 * Created by huylv on 24/02/2016.
 */
public class WelcomePresenterImpl implements WelcomePresenter {
    private final WelcomeView mView;

    public WelcomePresenterImpl(WelcomeView v){mView=v;}

    @Override
    public void getCurrentUser() {

        mView.onGetCurrentUserSuccess(Session.getCurrentUser());
    }
}
