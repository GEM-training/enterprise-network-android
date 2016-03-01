package gem.training3.enterprisenetwork.screen.fragment.allstore;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.StoreAdapter;
import gem.training3.enterprisenetwork.adapter.listener.OnLoadMoreListener;
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

    @Bind(R.id.store_list_srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Store> allStoreList;
    private StoreAdapter adapter;
    private static int currentPage;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allStoreList = new ArrayList<>();
        currentPage=0;
        LinearLayoutManager llm =  new LinearLayoutManager(getActivity());
        store_list_stores_rv.setLayoutManager(llm);
        adapter = new StoreAdapter(allStoreList,getActivity(),store_list_stores_rv);
        store_list_stores_rv.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage=0;
                getPresenter().getAllStore();
            }
        });

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                allStoreList.add(null);
                adapter.notifyItemInserted(allStoreList.size() - 1);

                currentPage+=1;
                Log.e("cxz",":ssss:"+currentPage);
                getPresenter().loadMoreStore(currentPage);
            }
        });
//        store_list_stores_urv.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getPresenter().getAllStore();
//            }
//        });
//
//        store_list_stores_urv.enableLoadmore();
//        //page start from 0
//        currentPage = 0;
//        store_list_stores_urv.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
//            @Override
//            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
////                currentPage+=1;
////                getPresenter().loadMoreStore(currentPage);
//            }
//        });
//
        getPresenter().getAllStore();

//        adapter.enableLoadMore();
//        adapter.setCustomLoadMoreView(LayoutInflater.from(getActivity())
//                .inflate(R.layout.bottom_progressbar, null));
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
        this.allStoreList.clear();
        this.allStoreList.addAll(storeArrayList);
        adapter.notifyDataSetChanged();
        store_total_number_tv.setText(String.valueOf(allStoreList.size()));

        swipeRefreshLayout.setRefreshing(false);
        store_list_pb.setVisibility(View.GONE);
        store_list_stores_rv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadMoreSuccess(ArrayList<Store> moreStore) {
        allStoreList.remove(allStoreList.size()-1);
        this.allStoreList.addAll(moreStore);
        adapter.notifyDataSetChanged();
        store_total_number_tv.setText(String.valueOf(allStoreList.size()));
        adapter.setLoaded();
    }


}
