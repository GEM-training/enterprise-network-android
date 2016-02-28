package gem.training3.enterprisenetwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;

/**
 * Created by huylv on 22/02/2016.
 */
public abstract class BaseActivityToolbar <T extends BasePresenter> extends BaseActivity<T> implements BaseView<T>{

    @Nullable
    @Bind(R.id.navigation_bar)
    protected RelativeLayout relativeLayout_navigation_bar;
    //    @Bind(R.id.tool_bar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
    }
}
