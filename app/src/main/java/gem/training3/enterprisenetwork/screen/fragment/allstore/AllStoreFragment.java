package gem.training3.enterprisenetwork.screen.fragment.allstore;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.StoreAdapter;
import gem.training3.enterprisenetwork.base.BaseFragment;
import gem.training3.enterprisenetwork.base.BasePresenter;
import gem.training3.enterprisenetwork.network.ServiceBuilder;
import gem.training3.enterprisenetwork.network.Session;
import gem.training3.enterprisenetwork.network.dto.ResponseStore;
import gem.training3.enterprisenetwork.network.dto.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 25/02/2016.
 */
public class AllStoreFragment extends BaseFragment {

    @Bind(R.id.product_list_rv)
    RecyclerView stores_list_stores_rv;

    ArrayList<Store> allStoreList;
    StoreAdapter adapter;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allStoreList = new ArrayList<>();

        Call<ResponseStore> call = ServiceBuilder.getService().getAllStore(Session.getCurrentUser().getToken());
        call.enqueue(new Callback<ResponseStore>() {
            @Override
            public void onResponse(Call<ResponseStore> call, Response<ResponseStore> response) {
                Gson gson = new Gson();
                ResponseStore ss = (ResponseStore) response.body();
                Store[] sss = ss.getContent();
                for(Store s : sss){
                    allStoreList.add(s);
                }
                adapter.notifyDataSetChanged();
//                Log.e("cxz","ssss"+sss[1].getName());
//                ResponseUserInfoDTO responseUserInfo = gson.fromJson(gson.toJson(responseDTO.getReturnObject()), ResponseUserInfoDTO.class);
            }

            @Override
            public void onFailure(Call<ResponseStore> call, Throwable t) {
                Log.e("cxz","xxxx"+t.getMessage());
            }
        });

        LinearLayoutManager llm =  new LinearLayoutManager(getActivity());
        stores_list_stores_rv.setLayoutManager(llm);
        adapter = new StoreAdapter(allStoreList,getActivity());
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
