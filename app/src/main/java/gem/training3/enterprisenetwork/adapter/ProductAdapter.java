package gem.training3.enterprisenetwork.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.network.dto.Product;

/**
 * Created by huylv on 25/02/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final ArrayList<Product> productArrayList;

    public ProductAdapter(ArrayList<Product> s) {
        productArrayList = s;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product item = productArrayList.get(position);
        holder.product_name_tv.setText(item.getName());
        holder.product_description_tv.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        final TextView product_name_tv;
        final TextView product_description_tv;
        public ProductViewHolder(View v){
            super(v);
            product_name_tv = (TextView)v.findViewById(R.id.store_name_tv);
            product_description_tv = (TextView)v.findViewById(R.id.store_description_tv);
        }

    }
}