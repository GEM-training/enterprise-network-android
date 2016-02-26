package gem.training3.enterprisenetwork.screen.fragment.allstore;

import java.util.ArrayList;

import gem.training3.enterprisenetwork.base.BaseView;
import gem.training3.enterprisenetwork.network.dto.Store;

/**
 * Created by huylv on 26-Feb-16.
 */
public interface AllStoreView extends BaseView<AllStorePresenter>{
    void onGetAllStoreSuccess(ArrayList<Store> storeArrayList);
}
