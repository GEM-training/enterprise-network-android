package gem.training3.enterprisenetwork.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;

/**
 * Created by huylv on 22/02/2016.
 */
public abstract class BaseActivityToolbar <T extends BasePresenter> extends BaseActivity<T> implements BaseView<T>{
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
    }
}
