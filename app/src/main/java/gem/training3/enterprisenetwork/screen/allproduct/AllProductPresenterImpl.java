package gem.training3.enterprisenetwork.screen.allproduct;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import gem.training3.enterprisenetwork.adapter.ProductAdapter;
import gem.training3.enterprisenetwork.base.log.EventLogger;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.common.util.DialogUtils;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.model.Product;
import gem.training3.enterprisenetwork.network.model.ResponseProduct;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by huylv on 26-Feb-16.
 */
public class AllProductPresenterImpl implements AllProductPresenter {
    final private AllProductView view;

    private ArrayList<Product> allProductList = new ArrayList<>();
    private ProductAdapter adapter;

    public AllProductPresenterImpl(AllProductView v) {
        view = v;
        adapter = new ProductAdapter((Context) view, allProductList, null);
    }

    @Override
    public void getProductByStoreId(Long storeId) {
        EventLogger.info("Getting product by store id:" + storeId);
        Call<ResponseProduct> call = ServiceBuilder.getService().getProductByStore(Session.getCurrentUser().getToken(), Session.getCurrentUser().getDeviceId(), storeId, 0, 10, Constants.columnNameAsc);
        call.enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                // Return product list of store with store id
                ResponseProduct responseProduct = response.body();
                Product[] products = responseProduct.getContent();
                ArrayList<Product> productArrayList = new ArrayList<>(Arrays.asList(products));
                view.onGetAllProductSuccess(productArrayList);
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                EventLogger.info("get product by store id failure:" + t.getMessage());
                DialogUtils.showErrorAlert((Activity) view, t.getMessage());
            }
        });

    }

    @Override
    public void loadMoreProduct(Long storeId, int currentPage) {
        EventLogger.info("Load more product...");
        Call<ResponseProduct> call = ServiceBuilder.getService().getProductByStore(Session.getCurrentUser().getToken(), Session.getCurrentUser().getDeviceId(), storeId, currentPage, Constants.SIZE_PAGE_STORE, Constants.columnNameAsc);
        call.enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                //load more product of store with page number
                ResponseProduct responseProduct = response.body();
                Product[] products = responseProduct.getContent();
                ArrayList<Product> moreProduct = new ArrayList<>(Arrays.asList(products));
                view.onLoadMoreSuccess(moreProduct);
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                EventLogger.info("Load more product failure: " + t.getMessage());
                view.onRequestError(t.getMessage());
            }
        });
    }

    @Override
    public ArrayList<Product> getAllProductList() {
        return allProductList;
    }

    @Override
    public ProductAdapter getAdapter() {
        return adapter;
    }
}
