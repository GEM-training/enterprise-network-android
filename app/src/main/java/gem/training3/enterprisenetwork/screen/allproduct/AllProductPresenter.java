package gem.training3.enterprisenetwork.screen.allproduct;

import gem.training3.enterprisenetwork.base.BasePresenter;

/**
 * Created by huylv on 26-Feb-16.
 */
public interface AllProductPresenter extends BasePresenter {
    void getProductByStoreId(Long storeId);
    void loadMoreProduct(Long storeId,int currentPage);
}
