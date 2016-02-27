package gem.training3.enterprisenetwork.screen.allproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.ProductAdapter;
import gem.training3.enterprisenetwork.base.BaseActivityToolbar;
import gem.training3.enterprisenetwork.network.model.Product;

/**
 * Created by huylv on 25/02/2016.
 */
public class AllProductActivity extends BaseActivityToolbar<AllProductPresenter> implements AllProductView{

    private ArrayList<Product> productArrayList;
    private ProductAdapter adapter;
    @Bind(R.id.product_list_rv)
    RecyclerView product_list_rv;

    @Bind(R.id.product_list_pb)
    ProgressBar product_list_pb;

    @Bind(R.id.products_total_number_tv)
    TextView products_total_number_tv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_products;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productArrayList = new ArrayList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        product_list_rv.setLayoutManager(llm);
        adapter = new ProductAdapter(productArrayList);
        product_list_rv.setAdapter(adapter);

        Intent i = getIntent();
        Long storeId = i.getLongExtra("storeId",1);

        getPresenter().getProductByStoreId(storeId);
    }

    @Override
    public void onGetAllProductSuccess(ArrayList<Product> productArrayList) {
        product_list_rv.setVisibility(View.VISIBLE);
        product_list_pb.setVisibility(View.GONE);
        this.productArrayList.addAll(productArrayList);
        adapter.notifyDataSetChanged();
        products_total_number_tv.setText(String.valueOf(productArrayList.size()));
    }

    @Override
    public AllProductPresenter onCreatePresenter() {
        return new AllProductPresenterImpl(this);
    }
}
