package gem.training3.enterprisenetwork.base;

/**
 * Base View
 * Created by neo on 2/5/2016.
 */
public interface BaseView<P extends BasePresenter> {
    void showProgress();
    void hideProgress();
    void onPrepareLayout();

    P getPresenter();
    P onCreatePresenter();

    void onRequestError(String errorMessage);
}
