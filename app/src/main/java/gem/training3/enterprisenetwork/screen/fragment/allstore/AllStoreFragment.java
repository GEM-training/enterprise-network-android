package gem.training3.enterprisenetwork.screen.fragment.allstore;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.StoreAdapter;
import gem.training3.enterprisenetwork.base.BaseFragment;
import gem.training3.enterprisenetwork.network.model.Store;

/**
 * Created by huylv on 25/02/2016.
 */
public class AllStoreFragment extends BaseFragment<AllStorePresenter> implements AllStoreView {

    @Bind(R.id.store_list_rv)
    RecyclerView store_list_stores_rv;

    @Bind(R.id.store_total_number_tv)
    TextView store_total_number_tv;

    @Bind(R.id.store_list_pb)
    ProgressBar store_list_pb;

    private ArrayList<Store> allStoreList;
    private StoreAdapter adapter;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allStoreList = new ArrayList<>();
        LinearLayoutManager llm =  new LinearLayoutManager(getActivity());
        store_list_stores_rv.setLayoutManager(llm);
        adapter = new StoreAdapter(allStoreList,getActivity());
        store_list_stores_rv.setAdapter(adapter);

        getPresenter().getAllStore();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_stores;
    }

    @Override
    public AllStorePresenter onCreatePresenter() {
        return new AllStorePresenterImpl(this);
    }

    @Override
    public void onGetAllStoreSuccess(ArrayList<Store> storeArrayList) {
        this.allStoreList.addAll(storeArrayList);
        adapter.notifyDataSetChanged();
        store_total_number_tv.setText(String.valueOf(allStoreList.size()));

        store_list_pb.setVisibility(View.GONE);
        store_list_stores_rv.setVisibility(View.VISIBLE);
    }
}
