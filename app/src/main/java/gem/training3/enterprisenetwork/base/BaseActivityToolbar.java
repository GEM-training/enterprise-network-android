package gem.training3.enterprisenetwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RelativeLayout;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;

/**
 * Created by huylv on 22/02/2016.
 */
public abstract class BaseActivityToolbar <T extends BasePresenter> extends BaseActivity<T> implements BaseView<T>{

//    @Bind(R.id.tool_bar)
    Toolbar toolBar;

    @Nullable
    @Bind(R.id.navibar)
    protected RelativeLayout rlNaviBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("cxz","find toolbar");
        toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
    }
}
