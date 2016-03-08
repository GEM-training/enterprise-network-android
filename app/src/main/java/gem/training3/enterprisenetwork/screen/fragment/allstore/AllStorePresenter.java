package gem.training3.enterprisenetwork.screen.fragment.allstore;

import gem.training3.enterprisenetwork.base.BasePresenter;

/**
 * Created by huylv on 26-Feb-16.
 */
public interface AllStorePresenter extends BasePresenter {
    void getAllStore();

    void loadMoreStore(int currentPage);
}
