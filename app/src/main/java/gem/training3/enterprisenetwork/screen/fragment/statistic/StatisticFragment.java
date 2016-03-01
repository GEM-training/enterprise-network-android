package gem.training3.enterprisenetwork.screen.fragment.statistic;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseFragment;

/**
 * Created by huylv on 01-Mar-16.
 */
public class StatisticFragment extends BaseFragment<StatisticPresenter> implements StatisticView {

    @Bind(R.id.table_item_purchased)
    TableLayout table_item_purchased;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistic;
    }

    @Override
    public StatisticPresenter onCreatePresenter() {
        return new StatisticPresenterImpl(this);
    }
}
