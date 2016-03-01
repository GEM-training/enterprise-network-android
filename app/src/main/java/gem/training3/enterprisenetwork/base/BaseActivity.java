package gem.training3.enterprisenetwork.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

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
    public void showProgress(ProgressBar pb,View content) {
        if(pb.getVisibility() == View.GONE && content.getVisibility() == View.VISIBLE ){
            pb.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgress(ProgressBar pb, View content) {
        if(pb.getVisibility() == View.VISIBLE && content.getVisibility() == View.GONE ){
            pb.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestError(String errorMessage) {
        DialogUtils.showErrorAlert(this, errorMessage);
    }


    /**
     * Return layout resource id for activity
     */
    protected abstract int getLayoutId();
}
