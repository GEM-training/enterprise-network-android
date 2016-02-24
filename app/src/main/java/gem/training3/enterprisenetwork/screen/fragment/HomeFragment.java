package gem.training3.enterprisenetwork.screen.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gem.training3.enterprisenetwork.R;


/**
 * Created by huylv on 17/02/2016.
 */
public class HomeFragment extends Fragment {

    TextView tvUserName;

    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        return v;
    }
}
