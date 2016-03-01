package gem.training3.enterprisenetwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.adapter.listener.OnLoadMoreListener;
import gem.training3.enterprisenetwork.common.Constants;
import gem.training3.enterprisenetwork.network.model.Store;
import gem.training3.enterprisenetwork.screen.allproduct.AllProductActivity;

/**
 * Created by huylv on 25/02/2016.
 */
public class StoreAdapter extends RecyclerView.Adapter {
    private final ArrayList<Store> storeArrayList;
    private final Context context;
    private boolean loading;
    private int visibleThreshold = 5;
    private OnLoadMoreListener onLoadMoreListener;
    private int lastVisibleItem, totalItemCount;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    public StoreAdapter(ArrayList<Store> s,Context c,RecyclerView recyclerView) {
        storeArrayList = s;
        context = c;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_store, parent, false);

            vh = new StoreViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.bottom_progressbar, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Store item = storeArrayList.get(position);
        if (holder instanceof StoreViewHolder) {
            ((StoreViewHolder) holder).store_name_tv.setText(item.getName());
            ((StoreViewHolder) holder).store_address_tv.setText(item.getAddress());
            ((StoreViewHolder) holder).store_layout_cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, AllProductActivity.class);
                    i.putExtra(Constants.intent_storeId,item.getId());
                    context.startActivity(i);
                }
            });
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return storeArrayList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.store_name_tv)
        TextView store_name_tv;
        @Bind(R.id.store_address_tv)
        TextView store_address_tv;
        @Bind(R.id.store_layout_cv)
        CardView store_layout_cv;

        public StoreViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.bottom_progress_bar);
        }
    }
}
