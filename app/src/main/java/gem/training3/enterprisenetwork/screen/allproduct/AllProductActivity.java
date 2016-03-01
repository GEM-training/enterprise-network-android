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
import gem.training3.enterprisenetwork.adapter.ProductAdapter;
import gem.training3.enterprisenetwork.adapter.listener.OnLoadMoreListener;
import gem.training3.enterprisenetwork.base.BaseActivityToolbar;
import gem.training3.enterprisenetwork.base.log.EventLogger;
import gem.training3.enterprisenetwork.network.model.Product;

/**
 * Created by huylv on 25/02/2016.
 */
public class AllProductActivity extends BaseActivityToolbar<AllProductPresenter> implements AllProductView{

    private ArrayList<Product> allProductList;
    private ProductAdapter adapter;
    @Bind(R.id.product_list_rv)
    RecyclerView product_list_rv;

    @Bind(R.id.product_list_pb)
    ProgressBar product_list_pb;

    @Bind(R.id.products_total_number_tv)
    TextView products_total_number_tv;

    @Bind(R.id.product_list_srl)
    SwipeRefreshLayout product_list_srl;

    private int currentPage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_products;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventLogger.info("All product activity create");
        allProductList = new ArrayList<>();
        currentPage = 0;
        LinearLayoutManager llm = new LinearLayoutManager(this);
        product_list_rv.setLayoutManager(llm);
        adapter = new ProductAdapter(this, allProductList,product_list_rv);
        product_list_rv.setAdapter(adapter);

        Intent i = getIntent();
        final Long storeId = i.getLongExtra("storeId",1);

        product_list_srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage =0;
                getPresenter().getProductByStoreId(storeId);
            }
        });
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                allProductList.add(null);
                adapter.notifyItemInserted(allProductList.size()-1);
                currentPage+=1;
                getPresenter().loadMoreProduct(currentPage);
            }
        });

        getPresenter().getProductByStoreId(storeId);
    }

    @Override
    public void onGetAllProductSuccess(ArrayList<Product> productArrayList) {
        allProductList.clear();
        EventLogger.info("Get all product successful, size:"+productArrayList.size());
        hideProgress(product_list_pb,product_list_rv);
        this.allProductList.addAll(productArrayList);
        adapter.notifyDataSetChanged();
        products_total_number_tv.setText(String.valueOf(productArrayList.size()));
    }

    @Override
    public void onLoadMoreSuccess(ArrayList<Product> moreProduct) {
        allProductList.remove(allProductList.size()-1);
        allProductList.addAll(moreProduct);
        EventLogger.info("Load more product successful, current size:"+ allProductList.size());
        adapter.notifyDataSetChanged();
        products_total_number_tv.setText(String.valueOf(allProductList.size()));
    }

    @Override
    public AllProductPresenter onCreatePresenter() {
        return new AllProductPresenterImpl(this);
    }
}
