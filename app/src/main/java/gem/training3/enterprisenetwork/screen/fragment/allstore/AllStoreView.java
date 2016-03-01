package gem.training3.enterprisenetwork.screen.fragment.allstore;

import java.util.ArrayList;

import gem.training3.enterprisenetwork.base.BaseView;
import gem.training3.enterprisenetwork.network.model.Store;

/**
 * Created by huylv on 26-Feb-16.
 */
interface AllStoreView extends BaseView<AllStorePresenter>{
    void onGetAllStoreSuccess(ArrayList<Store> storeArrayList);
    void onLoadMoreSuccess(ArrayList<Store> moreStore);
}
