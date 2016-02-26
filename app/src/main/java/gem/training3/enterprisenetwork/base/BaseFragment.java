package gem.training3.enterprisenetwork.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import gem.training3.enterprisenetwork.common.util.DialogUtils;

/**
 * Created by Hoak57uet on 2/20/2016.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView<T> {
    private ProgressDialog mProgressDialog;
    private T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Presenter for this view
        mPresenter = onCreatePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void showProgress() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onRequestError(String errorMessage) {
        DialogUtils.showErrorAlert(getActivity(),errorMessage);
    }

    @Override
    public void onPrepareLayout() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(android.R.style.Widget_DeviceDefault_Light_ProgressBar_Large);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public T getPresenter() {
        return mPresenter;
    }

    protected abstract int getLayoutId();

}
