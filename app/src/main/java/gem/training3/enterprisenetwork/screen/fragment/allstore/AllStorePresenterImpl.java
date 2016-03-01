package gem.training3.enterprisenetwork.screen.fragment.allstore;

import java.util.ArrayList;
import java.util.Arrays;

import gem.training3.enterprisenetwork.common.Constants;
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
        Call<ResponseStore> call = ServiceBuilder.getService().getStore(Session.getCurrentUser().getToken(),Session.getCurrentUser().getDeviceId(),0,Constants.SIZE_PAGE_STORE, Constants.columnNameAsc);

        call.enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
//                Logger.e("s"+response.raw().toString());
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

    @Override
    public void loadMoreStore(int currentPage) {
        Call<ResponseStore> call = ServiceBuilder.getService().getStore(Session.getCurrentUser().getToken(),Session.getCurrentUser().getDeviceId(),currentPage,Constants.SIZE_PAGE_STORE,Constants.columnNameAsc);
        call.enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                ResponseStore responseStore = response.body();
                Store[] stores = responseStore.getContent();
                ArrayList<Store> moreStore = new ArrayList<Store>(Arrays.asList(stores));

                view.onLoadMoreSuccess(moreStore);
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                view.onRequestError(t.getMessage());
            }
        });
    }
}
