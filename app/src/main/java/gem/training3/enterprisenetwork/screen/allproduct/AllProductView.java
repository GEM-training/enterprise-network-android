package gem.training3.enterprisenetwork.screen.allproduct;

import java.util.ArrayList;

import gem.training3.enterprisenetwork.base.BaseView;
import gem.training3.enterprisenetwork.network.dto.Product;

/**
 * Created by huylv on 26-Feb-16.
 */
interface AllProductView extends BaseView<AllProductPresenter> {
    void onGetAllProductSuccess(ArrayList<Product> productArrayList);
}
