package gem.training3.enterprisenetwork.screen.allproduct;

import java.util.List;

import gem.training3.enterprisenetwork.adapter.ProductAdapter;
import gem.training3.enterprisenetwork.base.BasePresenter;
import gem.training3.enterprisenetwork.network.model.Product;

/**
 * Created by huylv on 26-Feb-16.
 */
public interface AllProductPresenter extends BasePresenter {
    void getProductByStoreId(Long storeId);
    void loadMoreProduct(Long storeId,int currentPage);

    ProductAdapter getAdapter();

    List<Product> getAllProductList();
}
