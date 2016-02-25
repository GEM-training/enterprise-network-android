package gem.training3.enterprisenetwork.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import gem.training3.enterprisenetwork.common.util.DialogUtils;

/**
 * Base activity
 * Created by neo on 2/5/2016.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView<T> {
    private ProgressDialog mProgressDialog;
    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        onPrepareLayout();
        mPresenter = onCreatePresenter();
    }

    @Override
    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onPrepareLayout() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(android.R.style.Widget_DeviceDefault_Light_ProgressBar_Large);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);


    }

    @Override
    public void showProgress() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
    }

    @Override
    public void onRequestError(int errorCode, String errorMessage) {
        DialogUtils.showErrorAlert(this, errorMessage);
    }

    @Override
    public void onRequestSuccess() {
        hideProgress();
    }

    /**
     * Return layout resource id for activity
     */
    protected abstract int getLayoutId();
}
