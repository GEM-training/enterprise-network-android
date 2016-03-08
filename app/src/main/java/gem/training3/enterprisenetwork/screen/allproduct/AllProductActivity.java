package gem.training3.enterprisenetwork.screen.allproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.listener.OnLoadMoreListener;
import gem.training3.enterprisenetwork.base.BaseActivityToolbar;
import gem.training3.enterprisenetwork.base.log.EventLogger;
import gem.training3.enterprisenetwork.network.model.Product;

/**
 *
 * Created by huylv on 25/02/2016.
 */
public class AllProductActivity extends BaseActivityToolbar<AllProductPresenter> implements AllProductView, OnLoadMoreListener {

    //num of item visible in recylcer view
    private final int VISIBLE_THRESHOLD = 5;
    //true when recycler view is loading more product
    public boolean mIsLoading;

    @Bind(R.id.product_list_rv)
    RecyclerView mProductsRv;

    @Bind(R.id.product_list_pb)
    ProgressBar mProgressBar;

    @Bind(R.id.products_total_number_tv)
    TextView mTotalNumberTv;

    @Bind(R.id.product_list_srl)
    SwipeRefreshLayout mProductsRefreshLayout;

    private LinearLayoutManager mLayoutManager;
    //current page when load more product
    private int mCurrentPage;
    //get all product by mStoreid
    private long mStoreId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_products;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventLogger.info("All product activity create");
        mCurrentPage = 0;
        mLayoutManager = new LinearLayoutManager(this);
        mProductsRv.setLayoutManager(mLayoutManager);
        mProductsRv.setAdapter(getPresenter().getAdapter());

        Intent i = getIntent();
        mStoreId = i.getLongExtra("mStoreId", 1);

        mProductsRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentPage = 0;
                getPresenter().getProductByStoreId(mStoreId);
            }
        });
        getPresenter().getProductByStoreId(mStoreId);

        setLoadMoreListener();
    }

    /**
     *
     */
    private void setLoadMoreListener() {
        mProductsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = mLayoutManager.getItemCount();
                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                if (!mIsLoading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    // End has been reached
                    AllProductActivity.this.onLoadMore();
                    mIsLoading = true;
                }
            }
        });

    }


    @Override
    public void onGetAllProductSuccess(ArrayList<Product> productArrayList) {
        getPresenter().getAllProductList().clear();
        EventLogger.info("Get all product successful, size:"+productArrayList.size());
        hideProgress(mProgressBar, mProductsRv);
        getPresenter().getAllProductList().addAll(productArrayList);
        getPresenter().getAdapter().notifyDataSetChanged();
        mTotalNumberTv.setText(String.valueOf(productArrayList.size()));
    }

    @Override
    public void onLoadMoreSuccess(ArrayList<Product> moreProduct) {
        getPresenter().getAllProductList().remove(getPresenter().getAllProductList().size() - 1);
        getPresenter().getAllProductList().addAll(moreProduct);
        EventLogger.info("Load more product successful, current size:" + getPresenter().getAllProductList().size());
        getPresenter().getAdapter().notifyDataSetChanged();
        mTotalNumberTv.setText(String.valueOf(getPresenter().getAllProductList().size()));
    }

    @Override
    public AllProductPresenter onCreatePresenter() {
        return new AllProductPresenterImpl(this);
    }

    @Override
    public void onLoadMore() {
        getPresenter().getAllProductList().add(null);
        getPresenter().getAdapter().notifyItemInserted(getPresenter().getAllProductList().size() - 1);
        mCurrentPage += 1;
        getPresenter().loadMoreProduct(mStoreId, mCurrentPage);
    }
}
