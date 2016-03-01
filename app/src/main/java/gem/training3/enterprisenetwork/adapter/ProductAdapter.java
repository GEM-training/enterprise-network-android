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
import gem.training3.enterprisenetwork.network.model.Product;
import gem.training3.enterprisenetwork.screen.allproduct.AllProductActivity;

/**
 * Created by huylv on 25/02/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter {

    private final ArrayList<Product> productArrayList;
    private final Context context;
    private final int visibleThreshold = 5;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private int lastVisibleItem, totalItemCount;

    public ProductAdapter(Context c,ArrayList<Product> s, RecyclerView recyclerView) {
        productArrayList = s;
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

            vh = new ProductViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.bottom_progressbar, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Product item = productArrayList.get(position);
        if (holder instanceof ProductViewHolder) {
            ((ProductViewHolder) holder).product_name_tv.setText(item.getName());
            ((ProductViewHolder) holder).product_description_tv.setText(item.getDescription());
            ((ProductViewHolder) holder).product_layout_cv.setOnClickListener(new View.OnClickListener() {
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

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }
    @Override
    public int getItemViewType(int position) {
        return productArrayList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public final ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.bottom_progress_bar);
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.product_name_tv)
        TextView product_name_tv;
        @Bind(R.id.product_description_tv)
        TextView product_description_tv;
        @Bind(R.id.product_layout_cv)
        CardView product_layout_cv;
        public ProductViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }
    }
}