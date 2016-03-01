package gem.training3.enterprisenetwork.base;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Base View
 * Created by neo on 2/5/2016.
 */
public interface BaseView<P extends BasePresenter> {
    void showProgress(ProgressBar pb, View content);
    void hideProgress(ProgressBar pb, View content);
    void onPrepareLayout();

    P getPresenter();
    P onCreatePresenter();

    void onRequestError(String errorMessage);
}
