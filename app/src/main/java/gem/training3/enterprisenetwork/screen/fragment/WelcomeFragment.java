package gem.training3.enterprisenetwork.screen.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;

/**
 * Created by huylv on 24/02/2016.
 */
public class WelcomeFragment extends Fragment{

    @Bind(R.id.welcome_email_tv)
    TextView welcome_email_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_welcome,container,false);

        return v;
    }
}
