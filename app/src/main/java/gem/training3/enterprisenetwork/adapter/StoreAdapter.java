package gem.training3.enterprisenetwork.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.network.dto.Store;

/**
 * Created by huylv on 25/02/2016.
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    ArrayList<Store> storeArrayList;

    public StoreAdapter(ArrayList<Store> s) {
        storeArrayList = s;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store,parent,false);
        StoreViewHolder customerHolder = new StoreViewHolder(v);
        return customerHolder;
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        final Store item = storeArrayList.get(position);
        holder.product_name_tv.setText(storeArrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {
        TextView product_name_tv;
        TextView product_address_tv;
        TextView product_phone_tv;
        public StoreViewHolder(View v){
            super(v);
            product_name_tv = (TextView)v.findViewById(R.id.product_name_tv);
            product_address_tv = (TextView)v.findViewById(R.id.product_description_tv);
            product_phone_tv = (TextView)v.findViewById(R.id.product_phone_tv);

        }

    }
}
