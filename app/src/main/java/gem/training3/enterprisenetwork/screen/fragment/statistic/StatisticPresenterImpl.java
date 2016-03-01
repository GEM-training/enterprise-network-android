package gem.training3.enterprisenetwork.screen.fragment.statistic;

import gem.training3.enterprisenetwork.base.log.EventLogger;

/**
 * Created by huylv on 29-Feb-16.
 */
public class StatisticPresenterImpl implements StatisticPresenter {
    private final StatisticView view;
    public StatisticPresenterImpl(StatisticView v){view = v;}


    @Override
    public void loadTablePurchased() {
        EventLogger.info("Loading table purchased...");

        view.onLoadTableSuccess();
    }

    @Override
    public void loadLineChart() {
        EventLogger.info("Loading line chart...");
    }

    @Override
    public void loadBarChart() {
        EventLogger.info("Loading bar chart...");
    }
}
