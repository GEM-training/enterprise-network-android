package gem.training3.enterprisenetwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.network.dto.Store;
import gem.training3.enterprisenetwork.screen.allproduct.AllProductActivity;

/**
 * Created by huylv on 25/02/2016.
 */
public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private final ArrayList<Store> storeArrayList;
    private final Context context;

    public StoreAdapter(ArrayList<Store> s,Context c) {
        storeArrayList = s;
        context = c;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store,parent,false);
        return new StoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, final int position) {
        final Store item = storeArrayList.get(position);
        holder.store_name_tv.setText(storeArrayList.get(position).getName());
        holder.store_address_tv.setText(item.getAddress());
        holder.store_layout_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AllProductActivity.class);
                i.putExtra("storeId",item.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {
        final TextView store_name_tv;
        final TextView store_address_tv;
        final CardView store_layout_cv;
        public StoreViewHolder(View v){
            super(v);
            store_layout_cv = (CardView)v.findViewById(R.id.store_layout_cv);
            store_name_tv = (TextView)v.findViewById(R.id.store_name_tv);
            store_address_tv = (TextView)v.findViewById(R.id.store_description_tv);

        }

    }
}
