package gem.training3.enterprisenetwork.screen.fragment.allstore;

import java.util.ArrayList;
import java.util.Arrays;

import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.model.ResponseStore;
import gem.training3.enterprisenetwork.network.model.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 26-Feb-16.
 */
public class AllStorePresenterImpl implements AllStorePresenter {
    private final AllStoreView view;

    public AllStorePresenterImpl(AllStoreView v){view = v;}

    @Override
    public void getAllStore() {
        Call<ResponseStore> call = ServiceBuilder.getService().getStore(Session.getCurrentUser().getToken(),0,10);
        call.enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                ResponseStore responseStore = response.body();
                Store[] stores = responseStore.getContent();
                ArrayList<Store> storeArrayList = new ArrayList<>(Arrays.asList(stores));
                view.onGetAllStoreSuccess(storeArrayList);
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                view.onRequestError(t.getMessage());
            }
        });
    }
}
