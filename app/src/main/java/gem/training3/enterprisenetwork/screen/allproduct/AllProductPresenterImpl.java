package gem.training3.enterprisenetwork.screen.allproduct;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;

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
 * Created by huylv on 26-Feb-16.
 */
public class AllProductPresenterImpl implements AllProductPresenter {
    final private AllProductView view;
    public AllProductPresenterImpl(AllProductView v){view=v;}

    @Override
    public void getProductByStoreId(Long storeId) {
        Call<ResponseProduct> call = ServiceBuilder.getService().getProductByStore(Session.getCurrentUser().getToken(),Session.getCurrentUser().getDeviceId(),storeId,0,10, Constants.columnNameAsc);
        call.enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                ResponseProduct responseProduct = response.body();
                Product[] products = responseProduct.getContent();
                ArrayList<Product> productArrayList = new ArrayList<>(Arrays.asList(products));
                view.onGetAllProductSuccess(productArrayList);
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                DialogUtils.showErrorAlert((Activity)view,t.getMessage());
            }
        });

    }
}
