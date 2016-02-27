package gem.training3.enterprisenetwork.screen.fragment.welcome;

import gem.training3.enterprisenetwork.base.BaseView;
import gem.training3.enterprisenetwork.network.model.ResponseUserInfoDTO;

/**
 * Created by huylv on 24/02/2016.
 */
interface WelcomeView extends BaseView<WelcomePresenter> {
    void onGetCurrentUserSuccess(ResponseUserInfoDTO u);
}
