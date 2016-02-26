package gem.training3.enterprisenetwork.screen.allproduct;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;

import gem.training3.enterprisenetwork.common.util.DialogUtils;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.dto.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 26-Feb-16.
 */
public class AllProductPresenterImpl implements AllProductPresenter {
    private AllProductView view;
    public AllProductPresenterImpl(AllProductView v){view=v;}

    @Override
    public void getProductByStoreId(int storeId) {
        Call<Product[]> call = ServiceBuilder.getService().getProductByStore(Session.getCurrentUser().getToken(),storeId);
        call.enqueue(new Callback<Product[]>() {
            @Override
            public void onResponse(Call<Product[]> call, Response<Product[]> response) {
                Product[] ps = response.body();
                ArrayList<Product> productArrayList = new ArrayList<>(Arrays.asList(ps));
                view.onGetAllProductSuccess(productArrayList);
            }

            @Override
            public void onFailure(Call<Product[]> call, Throwable t) {
                DialogUtils.showErrorAlert((Activity)view,t.getMessage());
            }
        });

    }
}
