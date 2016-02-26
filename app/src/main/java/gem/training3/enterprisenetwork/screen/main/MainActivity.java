package gem.training3.enterprisenetwork.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.OnClick;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseActivityDrawer;
import gem.training3.enterprisenetwork.common.util.VarUtils;
import gem.training3.enterprisenetwork.screen.fragment.allstore.AllStoreFragment;
import gem.training3.enterprisenetwork.screen.fragment.welcome.WelcomeFragment;
import gem.training3.enterprisenetwork.screen.menunavi.NaviActivity;
import gem.training3.enterprisenetwork.screen.welcome.WelcomeActivity;

/**
 * Created by huylv on 22/02/2016.
 */
public class MainActivity extends BaseActivityDrawer<MainPresenter> implements MainView {

    private WelcomeFragment welcomeFragment;
    private AllStoreFragment allStoreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        welcomeFragment = new WelcomeFragment();
        allStoreFragment = new AllStoreFragment();
        getFragmentManager().beginTransaction().replace(R.id.main_fl,welcomeFragment).addToBackStack(null).commit();

        if(getSupportActionBar()!=null) getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter onCreatePresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (VarUtils.DOUBLE_BACK) {
                finish();
                return;
            }

            VarUtils.DOUBLE_BACK = true;
            Toast.makeText(this, getString(R.string.click_back_again), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    VarUtils.DOUBLE_BACK = false;
                }
            }, 2000);
        }

    }

    @OnClick(R.id.navi_menu_bt)
    void openNaviMenu(){
        Intent i = new Intent(MainActivity.this, NaviActivity.class);
        startActivity(i);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:
                getFragmentManager().beginTransaction().replace(R.id.main_fl,welcomeFragment).addToBackStack(null).commit();
                break;
            case R.id.nav_logout:
                getPresenter().doLogout(this);
                break;
            case R.id.nav_all_store:
                getFragmentManager().beginTransaction().replace(R.id.main_fl,allStoreFragment).addToBackStack(null).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onLogoutSuccess() {
        Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }

}
