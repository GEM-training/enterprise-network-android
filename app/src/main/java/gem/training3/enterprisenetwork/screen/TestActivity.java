package gem.training3.enterprisenetwork.screen;

import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseActivityDrawer;
import gem.training3.enterprisenetwork.base.BasePresenter;

/**
 * Created by huylv on 24/02/2016.
 */
public class TestActivity extends BaseActivityDrawer {

    @Override
    protected int getLayoutId() {
        return R.layout.testlayout;
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }
}
