package gem.training3.enterprisenetwork.screen.fragment.allstore;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.StoreAdapter;
import gem.training3.enterprisenetwork.base.BaseFragment;
import gem.training3.enterprisenetwork.base.BasePresenter;
import gem.training3.enterprisenetwork.network.dto.Store;

/**
 * Created by huylv on 25/02/2016.
 */
public class AllStoreFragment extends BaseFragment {

    @Bind(R.id.stores_list_stores_rv)
    RecyclerView stores_list_stores_rv;

    ArrayList<Store> allStoreList;
    StoreAdapter adapter;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allStoreList = new ArrayList<>();
        LinearLayoutManager llm =  new LinearLayoutManager(getActivity());
        stores_list_stores_rv.setLayoutManager(llm);
        adapter = new StoreAdapter(allStoreList);
        stores_list_stores_rv.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_stores;
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    public void onRequestError(int errorCode, String errorMessage) {

    }

    @Override
    public void onRequestSuccess() {

    }
}
