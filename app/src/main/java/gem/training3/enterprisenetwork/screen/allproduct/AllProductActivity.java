package gem.training3.enterprisenetwork.screen.allproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.ProductAdapter;
import gem.training3.enterprisenetwork.base.BaseActivityToolbar;
import gem.training3.enterprisenetwork.base.BasePresenter;
import gem.training3.enterprisenetwork.common.util.DialogUtils;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.dto.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 25/02/2016.
 */
public class AllProductActivity extends BaseActivityToolbar {

    ArrayList<Product> productArrayList;
    ProductAdapter adapter;
    @Bind(R.id.product_list_rv)
    RecyclerView product_list_rv;

    @Bind(R.id.product_list_pb)
    ProgressBar product_list_pb;

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
        int storeId = i.getIntExtra("storeId",1);
        Call<Product[]> call = ServiceBuilder.getService().getProductByStore(Session.getCurrentUser().getToken(),storeId);
        call.enqueue(new Callback<Product[]>() {
            @Override
            public void onResponse(Call<Product[]> call, Response<Product[]> response) {
                Product[] ps = response.body();
                for(Product p:ps){
                    productArrayList.add(p);
                }
                adapter.notifyDataSetChanged();
                product_list_rv.setVisibility(View.VISIBLE);
                product_list_pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Product[]> call, Throwable t) {
                product_list_rv.setVisibility(View.VISIBLE);
                product_list_pb.setVisibility(View.GONE);
                DialogUtils.showErrorAlert(AllProductActivity.this,t.getMessage());
            }
        });
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }
}
