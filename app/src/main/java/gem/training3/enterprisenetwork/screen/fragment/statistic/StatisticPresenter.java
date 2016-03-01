package gem.training3.enterprisenetwork.screen.fragment.statistic;

import gem.training3.enterprisenetwork.base.BasePresenter;

/**
 * Created by huylv on 29-Feb-16.
 */
public interface StatisticPresenter extends BasePresenter {
    void loadTablePurchased();

    void loadLineChart();

    void loadBarChart();
}
