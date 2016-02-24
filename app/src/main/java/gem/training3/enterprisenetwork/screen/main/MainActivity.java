package gem.training3.enterprisenetwork.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseActivityDrawer;
import gem.training3.enterprisenetwork.common.util.DialogUtils;
import gem.training3.enterprisenetwork.common.util.VarUtils;
import gem.training3.enterprisenetwork.network.dto.ResponseUserInfoDTO;
import gem.training3.enterprisenetwork.screen.fragment.HomeFragment;
import gem.training3.enterprisenetwork.screen.fragment.welcome.WelcomeFragment;
import gem.training3.enterprisenetwork.screen.welcome.WelcomeActivity;

/**
 * Created by huylv on 22/02/2016.
 */
public class MainActivity extends BaseActivityDrawer<MainPresenter> implements MainView {

    public HomeFragment homeFragment;
    public WelcomeFragment welcomeFragment;

    ResponseUserInfoDTO currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getCurrentUser();

        homeFragment = new HomeFragment();
        welcomeFragment = new WelcomeFragment();
        getFragmentManager().beginTransaction().add(R.id.main_fl,welcomeFragment).addToBackStack(null).commit();

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
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
            if (VarUtils.DOUBLEBACK) {
                finish();
                return;
            }

            VarUtils.DOUBLEBACK = true;
            Toast.makeText(this, getString(R.string.click_back_again), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    VarUtils.DOUBLEBACK = false;
                }
            }, 2000);
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_camera:

                break;
            case R.id.nav_logout:
                getPresenter().doLogout(this);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onGetCurrentUserSuccess(ResponseUserInfoDTO u) {
        currentUser = u;
    }

    @Override
    public void onLogoutSuccess() {
        Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onNetworkError() {
        DialogUtils.showErrorAlert(this,getString(R.string.dialog_title_content_network_problem));
    }


}
